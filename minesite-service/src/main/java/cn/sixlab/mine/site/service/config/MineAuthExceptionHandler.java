package cn.sixlab.mine.site.service.config;

import cn.sixlab.mine.site.service.service.UserService;
import cn.sixlab.mine.site.common.utils.Err;
import cn.sixlab.mine.site.common.utils.ResultUtils;
import cn.sixlab.mine.site.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MineAuthExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {
    @Autowired
    UserService userService;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.info("================");
        WebUtils.writeJson(response, ResultUtils.error(Err.AUTH, "auth.access.denied").toString());
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.info("================");
        WebUtils.writeJson(response, ResultUtils.error(Err.AUTH, "login.not").toString());
    }
}
