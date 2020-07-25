package cn.sixlab.mine.site.service.config;

import cn.sixlab.mine.site.data.models.MsUser;
import cn.sixlab.mine.site.common.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MineAuthFilter extends BasicAuthenticationFilter {
    private UserUtils userUtils;

    public MineAuthFilter(AuthenticationManager authenticationManager, UserUtils userUtils) {
        super(authenticationManager);
        this.userUtils = userUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = userUtils.currentToken();

        if (StringUtils.isNotEmpty(token) && userUtils.isLogin(token)) {
            MsUser msUser = userUtils.loginedUser(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(msUser,
                    null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            userUtils.writeToken(token);
        }

        chain.doFilter(request, response);
    }
}
