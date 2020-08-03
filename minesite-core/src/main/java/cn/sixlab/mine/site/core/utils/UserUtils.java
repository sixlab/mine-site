package cn.sixlab.mine.site.core.utils;

import cn.sixlab.mine.site.data.mapper.MsUserMapper;
import cn.sixlab.mine.site.data.models.MsUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class UserUtils {
    private String TOKEN_KEY = "MS_TOKEN";

    @Autowired
    private MsUserMapper userMapper;

    @Value("${minesite.login.seconds}")
    private int expire;

    public String createToken(MsUser user) {
        String uuid = UUID.randomUUID().toString();

        userMapper.updateToken(user.getId(), uuid, expire);

        return uuid;
    }

    public String currentToken() {
        HttpServletRequest request = WebUtils.getRequest();

        // 先读取 request 防 cookie 缓存
        String token = request.getParameter(TOKEN_KEY);

        if(StringUtils.isEmpty(token)){
            token = request.getHeader(TOKEN_KEY);
        }

        // 如果没有，读取 cookie
        if (StringUtils.isEmpty(token)) {
            token = WebUtils.getCookie(TOKEN_KEY);
        } else {
            // 防止 cookie 缓存其他 uuid
            WebUtils.addCookie(TOKEN_KEY, token, expire);
        }

        return token;
    }

    public MsUser loginedUser() {
        String token = currentToken();
        return loginedUser(token);
    }

    public MsUser loginedUser(String token) {
        MsUser user = userMapper.selectByToken(token);
        if (null != user) {
            user.setPassword(null);
        }
        return user;
    }

    public String loginedUsername() {
        return loginedUser().getUsername();
    }

    public Integer loginedUserId() {
        return loginedUser().getId();
    }

    public boolean isLogin() {
        String token = currentToken();
        return isLogin(token);
    }

    public boolean isLogin(String token) {
        MsUser msUser = loginedUser(token);
        return msUser != null;
    }

    public void logout() {
        String token = currentToken();

        if (StringUtils.isNotEmpty(token)) {
            userMapper.delToken(token);
        }

        WebUtils.addCookie(TOKEN_KEY, "", 1);
        SecurityContextHolder.clearContext();
    }

    public String login(MsUser msUser) {
        String token = createToken(msUser);

        writeToken(token);

        return token;
    }

    public void writeToken(String token) {
        WebUtils.addCookie(TOKEN_KEY, token, expire);
    }

}
