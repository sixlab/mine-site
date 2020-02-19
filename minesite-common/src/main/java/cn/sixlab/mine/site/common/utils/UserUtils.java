package cn.sixlab.mine.site.common.utils;

import cn.sixlab.mine.site.data.models.MsUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class UserUtils {
    private static String TOKEN_KEY = "MS_USER";

    public static Integer loginedUserId() {
        return readTokenUser().getId();
    }

    public static MsUser readTokenUser() {
        String token = readToken();
        return readTokenUser(token);
    }

    public static MsUser readTokenUser(String token) {
        try {
            String subject = Jwts.parser()
                    .setSigningKey(signKey())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            MsUser msUser = JsonUtils.toBean(subject, MsUser.class);

            if (null != msUser) {
                return msUser;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public static boolean isLogin() {
        String token = readToken();
        return isLogin(token);
    }

    public static boolean isLogin(String token) {
        MsUser msUser = readTokenUser(token);
        return msUser != null;
    }

    public static void logout() {
        WebUtils.addCookie(TOKEN_KEY, "", 1);
        SecurityContextHolder.clearContext();
    }

    public static String login(MsUser msUser) {
        msUser.setPassword(null);
        String token = createToken(JsonUtils.toJson(msUser));

        writeToken(token);

        return token;
    }

    private static String createToken(String username) {
        Date expiration = new Date(System.currentTimeMillis() + expire() * 1000 + 60000);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, signKey())
                .compact();
    }

    public static void writeToken(String token) {
        WebUtils.addCookie(TOKEN_KEY, token, expire());
    }

    public static String readToken() {
        HttpServletRequest request = WebUtils.getRequest();

        // 先读取 request 防 cookie 缓存
        String token = request.getParameter(TOKEN_KEY);

        // 如果没有，读取 cookie
        if (StringUtils.isEmpty(token)) {
            token = WebUtils.getCookie(TOKEN_KEY);
        } else {
            // 防止 cookie 缓存其他 uuid
            WebUtils.addCookie(TOKEN_KEY, token, expire());
        }

        return token;
    }

    private static int expire() {
        return Integer.valueOf(Ctx.getProperty("minesite.login.seconds"));
    }

    private static String signKey() {
        return Ctx.getProperty("minesite.login.sign");
    }
}
