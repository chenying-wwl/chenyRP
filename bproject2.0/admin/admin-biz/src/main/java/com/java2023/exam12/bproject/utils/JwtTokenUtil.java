package com.java2023.exam12.bproject.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.util.Base64Util;

import java.util.Date;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
public class JwtTokenUtil {
    public static final String ACCOUNT ="account";
    public static final String TOKEN_HEADER = "Authorization";

    //private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    public  static long accessTokenExpireTime = 1000*60*5; //5分钟
    public static long refreshTokenExpireTime =1000*60*60;//7天
    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static  String encryptJWTKey = "secret" ;

    /**
     * 校验token是否正确
     *
     * @param token
     *            Token
     * @return boolean 是否正确
     */
    public static boolean verify(String token,String username) {
        try {
            // 帐号加JWT私钥解密
            String secret = getSubject(token) + Base64Util.encode(encryptJWTKey);
            // System.out.println("serect:"+secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            //比对用户账号
            if(getSubject(token).equals(username)){
                return true;
            }
        } catch (TokenExpiredException e) {
            throw e;
        }
        return false;
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @return java.lang.String
     */
    public  static String getSubject(String token) {
        DecodedJWT jwt = JWT.decode(token);
        // 只能输出String类型，如果是其他类型返回null
        return jwt.getSubject();
    }

    /**
     * 获取发布的时间
     * @param token
     * @return
     */
    public static Date getIssuedAt(String token) {

        DecodedJWT jwt = JWT.decode(token);
        // 只能输出String类型，如果是其他类型返回null
        return jwt.getIssuedAt();
    }

    public static String getClaim(String token, String claim) {
        DecodedJWT jwt = JWT.decode(token);
        // 只能输出String类型，如果是其他类型返回null
        return jwt.getClaim(claim).asString();
    }
    /**
     * 生成签名
     * @param account 帐号
     * @return 返回加密的Token
     */
    public static String sign(String account)  {
        //设置时间
        Date currentDate = new Date();
        Date expireDate =  new Date(currentDate.getTime() + accessTokenExpireTime);
        return sign(account,currentDate,expireDate);
    }

    /**
     * 生成签名
     * @param account 帐号
     * @param currentDate 当前时间
     * @param expireDate 过期时间
     * @return 返回加密的Token
     */
    public static String sign(String account,Date currentDate,Date expireDate)  {

        // 帐号加JWT私钥加密
        String secret = account + Base64Util.encode(encryptJWTKey);
        // System.out.println("serect:"+secret);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带account帐号信息
        return JWT.create()
                .withSubject(account)
                .withIssuedAt(currentDate)
                .withClaim(ACCOUNT, account)
                .withExpiresAt(expireDate).sign(algorithm);

    }

    public static String getAccount(String token) {
        return getClaim(token, ACCOUNT);
    }

    public static String getAccessToken(String account) {
        return getAccessToken(account,new Date());
    }
    public static String getAccessToken(String account,Date currentDate) {
        //设置时间
        Date expireDate =  new Date(currentDate.getTime() + accessTokenExpireTime);
        return sign(account,currentDate,expireDate);
    }

    public static String getRefreshToken(String account,Date currentDate) {
        Date expireDate =  new Date(currentDate.getTime() + refreshTokenExpireTime);
        return sign(account,currentDate,expireDate);
    }

}
