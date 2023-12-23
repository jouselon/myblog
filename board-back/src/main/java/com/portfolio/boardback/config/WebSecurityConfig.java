package com.portfolio.boardback.config;

import com.portfolio.boardback.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  // SecurityFilterChain을 생성하는 메서드
  @Bean
  protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors().and() //CORS(Cross-Origin Resource Sharing) 설정을 활성화
        .csrf().disable() //CSRF(Cross-Site Request Forgery) 보호를 비활성화
        .httpBasic().disable()
//기본적으로 Spring Security는 HTTP Basic 인증을 사용하도록 설정되어 있습니다.
//이를 비활성화하면 사용자 이름과 비밀번호를 사용한 기본 인증이 사용되지 않습니다.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//Spring Security에서 세션 관리를 설정하는 부분
//sessionManagement(): 세션 관리를 설정하는 메서드입니다.
//sessionCreationPolicy(SessionCreationPolicy.STATELESS): 세션 생성 정책을 설정합니다. //STATELESS는 상태가 없는 세션을 의미합니다.
//즉, 서버는 클라이언트의 상태를 유지하지 않고 각 요청은 독립적으로 처리됩니다.

        .authorizeRequests()

        // 특정 경로에 대해 모든 사용자에게 인증 없이 접근을 허용합니다.
        .antMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()

        // 특정 HTTP 메서드(GET)에 대해 특정 경로에 대한 모든 사용자에게 인증 없이 접근을 허용
        .antMatchers(HttpMethod.GET, "/api/v1/board/**", "/api/v1/user/*").permitAll()

        // 나머지 요청은 인증이 필요함
        .anyRequest().authenticated().and()

        // 인증에 실패했을 때 처리하는 핸들러를 등록
        .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint());

    // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
    httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    // SecurityFilterChain 반환
    return httpSecurity.build();
  }
}

// 인증 실패 시 처리하는 핸들러
//AuthenticationEntryPoint는 Spring Security에서 인증에 실패한 경우(즉, 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때) 처리를 담당하는 인터페이스
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

    // 인증 실패 응답 작성
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write("{ \"code\":\"AF\", \"message\": \"Authorization Failed\" }");
  }
}