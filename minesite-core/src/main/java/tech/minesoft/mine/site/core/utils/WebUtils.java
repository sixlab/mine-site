package tech.minesoft.mine.site.core.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class WebUtils {

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (null != requestAttributes) {
                return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        try {
            ServletRequestAttributes requestAttributes = getRequestAttributes();

            if (null != requestAttributes) {
                return requestAttributes.getRequest();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletResponse getResponse() {
        try {
            ServletRequestAttributes requestAttributes = getRequestAttributes();

            if (null != requestAttributes) {
                return requestAttributes.getResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUA() {
        HttpServletRequest req = getRequest();

        String ua = req.getHeader("user-agent");
        if (ua != null) {
            ua = ua.toLowerCase();
        }

        return ua;
    }

    public static String getIP() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Cdn-Src-Ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static void addCookie(String key, String val) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge((int) Seconds.m30);
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static void addCookie(String key, String val, int seconds) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge(seconds);
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static Cookie[] getCookies() {
        return getRequest().getCookies();
    }

    public static String getCookie(String key) {
        Cookie[] cookies = getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public static String requestBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static void writeJson(HttpServletResponse response, String json) {
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();

            writer.write(json);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJson(String json) {
        HttpServletResponse response = getResponse();

        writeJson(response, json);
    }
}
