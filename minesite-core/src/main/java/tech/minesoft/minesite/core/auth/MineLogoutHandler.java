package tech.minesoft.minesite.core.auth;

import tech.minesoft.minesite.core.utils.UserUtils;
import tech.minesoft.minesite.core.utils.WebUtils;
import tech.minesoft.minesite.core.vo.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MineLogoutHandler implements LogoutHandler, LogoutSuccessHandler {

    @Autowired
    UserUtils userUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        WebUtils.writeJson(response, ResultJson.successMsg("common.logout.success").toString());
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        userUtils.logout();
    }
}
