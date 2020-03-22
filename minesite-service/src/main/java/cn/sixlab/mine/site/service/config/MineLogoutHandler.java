package cn.sixlab.mine.site.service.config;

import cn.sixlab.mine.site.common.utils.UserUtils;
import cn.sixlab.mine.site.common.utils.WebUtils;
import cn.sixlab.mine.site.common.vo.ResultJson;
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
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        WebUtils.writeJson(response, ResultJson.successMsg("common.logout.success").toString());
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        UserUtils.logout();
    }
}
