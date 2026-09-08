
package co.yixiang.modules.security.security;

import co.yixiang.modules.security.config.SecurityProperties;
import co.yixiang.modules.security.security.vo.JwtUser;
import co.yixiang.utils.RedisUtils;
import co.yixiang.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * Token 工具类
 *
 * @author lioncity
 */

@Component
public class TokenUtil {
    @Autowired
    private SecurityProperties properties;

    /**
     * Logger
     */

    protected static final Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);


    /**
     * 权限缓存前缀
     */
    private static final String REDIS_PREFIX_AUTH = "auth:";

    /**
     * 用户信息缓存前缀
     */
    private static final String REDIS_PREFIX_USER = "user-details:";

    /**
     * redis repository
     */
    @Autowired
    private RedisUtils redisUtils;

    private SecretKey signingKey() {
        try {
            // JJWT 0.12 requires >= 512-bit key for HS512; derive deterministically from configured secret
            byte[] keyBytes = java.security.MessageDigest.getInstance("SHA-512")
                    .digest(properties.getSecret().getBytes(StandardCharsets.UTF_8));
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取用户名
     *
     * @param token Token
     * @return String
     */

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取过期时间
     *
     * @param token Token
     * @return Date
     */

    public Date getExpiredFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getExpiration() : null;
    }

    /**
     * 获得 Claims
     *
     * @param token Token
     * @return Claims
     */

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(signingKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            LOGGER.warn("getClaimsFromToken exception", e);
            claims = null;
        }
        return claims;
    }

    /**
     * 计算过期时间
     *
     * @return Date
     */
    private Date generateExpired() {
        return new Date(System.currentTimeMillis() + properties.getTokenValidityInSeconds() * 1000);
    }

    /**
     * 判断 Token 是否过期
     *
     * @param token Token
     * @return Boolean
     */

    private Boolean isTokenExpired(String token) {
        Date expirationDate = getExpiredFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 生成 Token
     *
     * @param userDetails 用户信息
     * @return String
     */

    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .expiration(generateExpired())
                .signWith(signingKey())
                .compact();

        String key = REDIS_PREFIX_AUTH + userDetails.getUsername() + ":" + token;
        redisUtils.set(key, token, properties.getTokenValidityInSeconds() / 1000);
        putUserDetails(userDetails);
        return token;
    }

    /**
     * 验证 Token
     *
     * @param token Token
     * @return Boolean
     */

    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + username+ ":" + token;
        Object data = redisUtils.get(key);
        String redisToken = data == null ? null : data.toString();
        return StringUtils.isNotEmpty(token) && !isTokenExpired(token) && token.equals(redisToken);
    }

    /**
     * 移除 Token
     *
     * @param token Token
     */

    public void removeToken(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + username+ ":" + token;
        redisUtils.del(key);
        delUserDetails(username);
    }

    /**
     * 获得用户信息 Json 字符串
     *
     * @param token Token
     * @return String
     */

    protected String getUserDetailsString(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_USER + username;
        Object data = redisUtils.get(key);
        return data == null ? null : data.toString();
    }

    /**
     * 获得用户信息
     *
     * @param token Token
     * @return UserDetails
     */

    public UserDetails getUserDetails(String token) {
        String userDetailsString = getUserDetailsString(token);
        if (userDetailsString != null) {
            return new Gson().fromJson(userDetailsString, JwtUser.class);
        }
        return null;
    }

    /**
     * 存储用户信息
     *
     * @param userDetails 用户信息
     */

    private void putUserDetails(UserDetails userDetails) {
        String key = REDIS_PREFIX_USER + userDetails.getUsername();
        redisUtils.set(key, new Gson().toJson(userDetails), properties.getTokenValidityInSeconds() / 1000);
    }


    /**
     * 删除用户信息
     *
     * @param username 用户名
     */

    private void delUserDetails(String username) {
        String key = REDIS_PREFIX_USER + username;
        redisUtils.del(key);
    }

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }

    public static void main(String[] args) {
        String key = Base64.getEncoder().encodeToString("123".getBytes());
        System.out.println(JSON.toJSONString(key));
    }
}
