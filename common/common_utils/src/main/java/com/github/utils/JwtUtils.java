package com.github.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/15
 */
public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24; // token过期时间
    public static final String APP_SECRET = "ukc8BDbSgxZDaY6pZFfWus2jJSPOPSQ"; // 秘钥加密

    /**
     * 生成token字符串
     * @param id 用户id
     * @param nickname 用户昵称
     * @return token字符串
     */
    public static String getJwtToken(String id, String nickname) {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT") // 头信息
                .setHeaderParam("alg", "HS256")
                .setSubject("online-user")
                .setIssuedAt(new Date()) // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id) // 设置token主体部分
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET) // 签名哈希
                .compact();
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken token
     * @return true 有效
     */
    public static boolean checkToken(String jwtToken) {
        if (ObjectUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request servlet request
     * @return true 有效
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (ObjectUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     *
     * @param request servlet request
     * @return 会员id
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (ObjectUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

    /**
     * 根据token获取会员id
     *
     * @param request servlet request
     * @return 会员id
     */
    public static String getUserIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("web-token");
        if (ObjectUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}

