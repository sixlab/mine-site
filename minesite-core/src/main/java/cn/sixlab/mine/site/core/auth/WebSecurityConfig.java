package cn.sixlab.mine.site.core.auth;

import cn.sixlab.mine.site.core.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    MineLogoutHandler logoutHandler;

    @Autowired
    MineAuthHandler authHandler;

    @Autowired
    MineAuthExceptionHandler authExceptionHandler;

    @Autowired
    UserUtils userUtils;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authHandler);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许被 iframe 嵌入
        http.headers().frameOptions().disable();

        //关闭csrf
        http.csrf().disable();

        http.cors().configurationSource(corsConfigurationSource());

        // 无状态
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 登录
        http.formLogin().loginProcessingUrl("/login")
                .successHandler(authHandler)
                .failureHandler(authHandler);

        // 退出
        http.logout().logoutUrl("/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutHandler);

        //任何请求,登录后可以访问
        http.authorizeRequests()
                .antMatchers(
                        "/*",
                        "/**/guest/**"
                ).permitAll()
                .anyRequest().authenticated();

        // 异常处理
        http.exceptionHandling()
                .accessDeniedHandler(authExceptionHandler)
                .authenticationEntryPoint(authExceptionHandler);

        // 校验过滤器
        http.addFilter(new MineAuthFilter(authenticationManager(), userUtils));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/druid/**")
                .antMatchers("/static/**")
                .antMatchers("/callback/**")
                .antMatchers("/favicon.ico");
    }

    // @Bean
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

}
