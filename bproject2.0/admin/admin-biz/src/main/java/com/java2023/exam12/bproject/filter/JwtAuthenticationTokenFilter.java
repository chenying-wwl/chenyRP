package com.java2023.exam12.bproject.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.util.ResponseUtils;
import com.java2023.exam12.bproject.utils.JwtTokenUtil;
import com.java2023.exam12.bproject.vo.ResultVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.java2023.exam12.bproject.vo.ResultVo.INVALID_TOKEN;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Component
public class JwtAuthenticationTokenFilter  extends OncePerRequestFilter {
    @Resource
    UserService userService;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        //1,从请求头中，获取认证信息
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        //2，验证登录信息
        if (token != null){
            //验证是否正确
            String account = JwtTokenUtil.getAccount(token);
            if(account!=null){
                if(SecurityContextHolder.getContext().getAuthentication() == null) {
                    //使用验证之后的用户账号进行登录
                    UserDetails userDetails = userService.loadUserByUsername(account);
                    //验证用户账号是否正确
                    try{
                        if (JwtTokenUtil.verify(token, userDetails.getUsername())) {
                            // 将用户信息存入 authentication，方便后续校验
                            // 创建带权限的token直接会定义成登陆成功
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }catch (TokenExpiredException e){
                        //验证一下，是否可以更换新的token
                        String oldToken = (String) stringRedisTemplate.opsForValue().get(
                                "user:"+userDetails.getUsername());
                        if(oldToken!=null&&oldToken.equals(token)){
                            //给用户换一个新的token
                            String newToken = JwtTokenUtil.sign(account);
                            //将新的token存储在redis中
                            stringRedisTemplate.opsForValue().set(
                                    "user:"+userDetails.getUsername() ,
                                    token);
                            //设置freshtoken过期时间
                            stringRedisTemplate.expire( "user:"+userDetails.getUsername() ,JwtTokenUtil.refreshTokenExpireTime, TimeUnit.MILLISECONDS);

                            // ResultVo result = new ResultVo(INVALID_TOKEN,"token已经失效了",null);
                            // ResponseUtils.writeJSON(response,result);
                            //将新的token发在头部
                            response.addHeader(JwtTokenUtil.TOKEN_HEADER,newToken);

                            // 将用户信息存入 authentication，方便后续校验
                            // 创建带权限的token直接会定义成登陆成功
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                            SecurityContextHolder.getContext().setAuthentication(authentication);

                        }else{
                            ResultVo resultVo = new ResultVo(INVALID_TOKEN,"token已经失效请重新登录",null);
                            ResponseUtils.writeJSON(response,resultVo);
                            return;
                        }

                    }
                }
            }
        }
        //3，放行
        filterChain.doFilter(request, response);
    }
}
