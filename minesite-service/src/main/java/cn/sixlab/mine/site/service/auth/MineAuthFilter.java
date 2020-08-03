package cn.sixlab.mine.site.service.auth;

import cn.sixlab.mine.site.core.utils.UserUtils;
import cn.sixlab.mine.site.core.vo.MineAuthority;
import cn.sixlab.mine.site.core.models.MsUser;
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

public class MineAuthFilter extends BasicAuthenticationFilter {
    private UserUtils userUtils;

    public MineAuthFilter(AuthenticationManager authenticationManager, UserUtils userUtils) {
        super(authenticationManager);
        this.userUtils = userUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 每次请求验证
        String token = userUtils.currentToken();

        if (StringUtils.isNotEmpty(token)) {
            MsUser msUser = userUtils.loginedUser(token);
            if (null != msUser) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(msUser,
                        null, MineAuthority.roles(msUser.getRole()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                userUtils.writeToken(token);
            }
        }

        chain.doFilter(request, response);
    }
}
