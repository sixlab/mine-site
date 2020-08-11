package tech.minesoft.mine.site.core.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.models.MsUser;
import tech.minesoft.mine.site.core.service.MsUserService;
import tech.minesoft.mine.site.core.utils.Err;
import tech.minesoft.mine.site.core.utils.I18nUtils;
import tech.minesoft.mine.site.core.utils.UserUtils;
import tech.minesoft.mine.site.core.utils.WebUtils;
import tech.minesoft.mine.site.core.vo.LoginUser;
import tech.minesoft.mine.site.core.vo.ResultJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MineAuthHandler implements AuthenticationProvider, AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Autowired
    MsUserService userService;

    @Autowired
    UserUtils userUtils;

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("登录请求，进行验证================");
        String username = authentication.getName().trim();
        String password = (String) authentication.getCredentials();

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            UserDetails userDetails = userService.loadUserByUsername(username);

            if (null != userDetails) {
                if (BCrypt.checkpw(password, userDetails.getPassword())) {
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                } else {
                    throw new AuthenticationServiceException(I18nUtils.get("login.wrong.password"));
                }
            }

            throw new UsernameNotFoundException(I18nUtils.get("login.not.exist"));
        }

        throw new AuthenticationServiceException(I18nUtils.get("login.empty"));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.info("Auth Fail================");
        String message;
        if (exception instanceof BadCredentialsException) {
            message = "login.bad.credentials";
        } else {
            message = exception.getMessage();
        }

        WebUtils.writeJson(response, ResultJson.error(Err.AUTH_MATCH, message).toString());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("Auth Success================");
        Object principal = authentication.getPrincipal();
        LoginUser loginUser = (LoginUser) principal;
        MsUser msUser = loginUser.getMsUser();

        String token = userUtils.login(msUser);

        WebUtils.writeJson(response, ResultJson.successData(token).toString());
    }
}
