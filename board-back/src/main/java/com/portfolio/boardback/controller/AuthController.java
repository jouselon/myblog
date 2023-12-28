package com.portfolio.boardback.controller;

import com.portfolio.boardback.dto.request.auth.SignInRequestDto;
import com.portfolio.boardback.dto.request.auth.SignUpRequestDto;
import com.portfolio.boardback.dto.response.auth.SignInResponseDto;
import com.portfolio.boardback.dto.response.auth.SignUpResponseDto;
import com.portfolio.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDto> signUp(
      @RequestBody @Valid SignUpRequestDto requestBody
      ) {
    ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
    return response;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDto> signIn(
      @RequestBody @Valid SignInRequestDto requestBody
      ){
    ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
    return response;
  }
}
