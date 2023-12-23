package com.portfolio.boardback.filter;

import com.portfolio.boardback.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal
      (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      // JWT 토큰 추출
      String token = parseBearerToken(request);

      // 토큰이 없으면 다음 필터로 이동
      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }

      // JWT 토큰 검증
      String email = jwtProvider.validate(token);

      // 토큰이 유효하지 않으면 다음 필터로 이동
      if (email == null) {
        filterChain.doFilter(request, response);
        return;
      }

      // 인증 토큰 생성
      AbstractAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      // 보안 컨텍스트 설정
      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authenticationToken);
      SecurityContextHolder.setContext(securityContext);

    } catch (Exception e) {
      // 예외 처리
      e.printStackTrace();
    }

    // 다음 필터로 이동
    filterChain.doFilter(request, response);
  }

  // Authorization 헤더에서 Bearer 토큰 추출
  private String parseBearerToken(HttpServletRequest request) {
    String authorization = request.getHeader("Authorization");

    // Authorization 헤더가 없으면 null 반환
    boolean hasAuthorization = StringUtils.hasText(authorization);
    if (!hasAuthorization) return null;

    // Bearer 토큰이 아니면 null 반환
    boolean isBearer = authorization.startsWith("Bearer ");
    if (!isBearer) return null;

    // "Bearer " 이후의 토큰 반환
    String token = authorization.substring(7);
    return token;
  }
}