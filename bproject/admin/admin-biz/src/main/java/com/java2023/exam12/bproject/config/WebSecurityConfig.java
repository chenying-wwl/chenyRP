package com.java2023.exam12.bproject.config;


import com.auth0.jwt.JWT;
import com.java2023.exam12.bproject.filter.JwtAuthenticationTokenFilter;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.util.ResponseUtils;
import com.java2023.exam12.bproject.utils.JwtTokenUtil;
import com.java2023.exam12.bproject.vo.ResultVo;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Configuration
//启动web的权限管理
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行swagger
        web.ignoring().antMatchers(HttpMethod.GET.toString(),
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/doc.html/**",
                "/webjars/**");
    }
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        http
                .authorizeRequests()//授权配置
                .anyRequest().authenticated() //需要权限验证
                .and().exceptionHandling()//.accessDeniedPage("/noright.html") //无权限的路径
                //没有访问权限的处理
                .accessDeniedHandler(new AccessDeniedHandler(){

                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        ResultVo resultVo = new ResultVo(501,"没有访问权限",null);
                        ResponseUtils.writeJSON(response,resultVo);
                    }
                })
                .and()
                .formLogin() //登录认证配置
                .loginProcessingUrl("/login.action").permitAll()//登陆请求
                //.defaultSuccessUrl("/index.jhtml")//登录成功之 后访问页面
                //登录成功之后的处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(
                            HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        //在登录成功之后自定义处理返回结果
                        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        String token = JwtTokenUtil.sign(userDetails.getUsername());

                        ResultVo resultVo =  ResultVo.ok("登录成功",token);
                        ResponseUtils.writeJSON(response,resultVo);
                    }
                })
                //登录失败的处理
                .failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        ResultVo resultVo =  ResultVo.fail("登录失败，原因："+exception.getMessage(),null);
                        ResponseUtils.writeJSON(response,resultVo);
                    }
                })
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login.html").permitAll() //登陆页面地址
                //.failureUrl("/fail.html")//登录失败的访问页面
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint(){

                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                            //没有登录，访问了页面，要返回没有登录的状态
                        ResultVo resultVo = new ResultVo(401,"用户没有登录,请首先登录",null);
                        ResponseUtils.writeJSON(response,resultVo);
                    }
                })
                //.and()
                .and().cors().and() //设置跨域
                .logout().permitAll()//退出功能
                .and().csrf().disable();//禁用csrf
    }


    //设置使用自定义的userService进行登录验证
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);

        //设置密码的加密
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

}
