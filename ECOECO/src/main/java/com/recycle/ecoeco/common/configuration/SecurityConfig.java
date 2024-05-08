package com.recycle.ecoeco.common.configuration;

import com.recycle.ecoeco.common.configuration.handler.CustomAuthenticationSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Component
@EnableWebSecurity
public class SecurityConfig {

    /* 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* 정적 리소스에 대한 요청은 제외하는 설정 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())
                /* 요청에 대한 권한 체크 */
                .authorizeHttpRequests( auth -> {
                    // 특정 URL에 대한 권한 설정
                    auth.requestMatchers("/login", "/user/mypage/selectUserById", "/joinus", "/user/mypage/findSearchInfo", "/user/mypage/findId","/user/mypage/findPwd", "/", "/main").permitAll();
                    auth.requestMatchers( "/manager/**").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN");
                    //나머지 URL은 모두 인증 필요
                    auth.anyRequest().authenticated();
//                    auth.anyRequest().permitAll();
                })
                .formLogin( login -> {
                    /* 로그인 페이지 설정 */
                    login.loginPage("/login");
                    /* 성공시 랜딩 페이지 설정 */
//                    login.defaultSuccessUrl("/");
                    login.successHandler(authenticationSuccessHandler()); // SuccessHandler 적용
                    /* 로그인 실패 시 랜딩 페이지 설정 */
                    login.failureForwardUrl("/user/mypage/loginfail");
                    /* 파라미터명 변경 */
                    login.usernameParameter("userId");
                    login.passwordParameter("userPwd");
                })
                .logout( logout -> {
                    /* 로그아웃 요청 URL */
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/mypage/logout"));
                    /* JSESSIONID 쿠키 삭제 */
                    logout.deleteCookies("JSESSIONID");
                    /* 세션 만료 */
                    logout.invalidateHttpSession(true);
                    /* 로그아웃 후 랜딩 페이지 */
                    logout.logoutSuccessUrl("/");
                })
                .build();
    }

    // SuccessHandler 빈 등록
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
