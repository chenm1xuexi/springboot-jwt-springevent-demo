package com.feifei.jwtdemo.config;

import com.feifei.jwtdemo.model.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Slf4j
public class JwtTokenUtil {

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Gibson ";

    public static final String JWT = "JWT";

    /**
     * 用于信息认证成功后，创建jwt Token
     *
     * @author shixiongfei
     * @date 2020-03-16
     * @updateDate 2020-03-16
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public static String createJwt(User user, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            // 生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            // 添加构成jwt的参数
            JwtBuilder builder = Jwts.builder()
                    // 设置jwt header
                    .setHeaderParam("typ", JWT)
                    // 设置jwt payload
                    // 基本信息，非敏感信息放到claims中
                    .claim("roleId", user.getRoleId())
                    .claim("userId", user.getUserId())
                    .claim("organizationId", user.getOrganizationId())
                    // 代表jwt的主题，即它的所有人
                    .setSubject(user.getUsername())
                    // 代表jwt签发主体
                    .setIssuer(audience.getClientId())
                    // 代表jwt的签发时间
                    .setIssuedAt(new Date())
                    // 签名
                    .signWith(signatureAlgorithm, signingKey);
            // 添加token过期时间
            long expireSecond = audience.getExpireSecond();

            if (expireSecond >= 0) {
                long TTLMillis = System.currentTimeMillis() + expireSecond;
                builder.setExpiration(new Date(TTLMillis))
                        .setNotBefore(new Date());
            }

            // 生成签名
            return TOKEN_PREFIX + builder.compact();
        } catch (Exception e) {
            log.error("获取签名失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析jwt token获取负载内容
     * @return
     */
    public static Claims parseJWT(String jwtWebToken, String base64Security) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jwtWebToken)
                    .getBody();

        } catch (ExpiredJwtException eje) {
            log.error("=== token过期 ===", eje);
            throw new RuntimeException(eje);
        } catch (Exception e) {
            log.error("=== token解析异常 ===", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从token中获取用户名
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     *  获取用户id
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Security) {
        Claims claims = parseJWT(token, base64Security);
        return claims.get("userId", String.class);
    }

    /**
     * 获取租户信息
     * @return
     */
    public static Tenant getTenant(String token, String base64Security) {
        Claims claims = parseJWT(token, base64Security);
        String organizationId = claims.get("organizationId", String.class);
        return new Tenant()
                .setOrganizationId(organizationId);
    }

    /**
     * 获取用户角色id
     * @param token
     * @param base64Security
     * @return
     */
    public static String getRole(String token, String base64Security) {
        Claims claims = parseJWT(token, base64Security);
        return claims.get("roleId", String.class);
    }

    /**
     * 判断token是否过期
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) {
        Claims claims = parseJWT(token, base64Security);
        return claims.getExpiration().before(new Date());
    }

    public static UserInfo getAll(String token, String base64Security) {
        Claims claims = parseJWT(token, base64Security);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(claims.get("userId", String.class))
                .setOrganizationId(claims.get("organizationId", String.class))
                .setRoleId(claims.get("roleId", String.class))
                .setUsername(claims.getSubject());
        return userInfo;
    }
}