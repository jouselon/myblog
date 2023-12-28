package com.portfolio.boardback.service;

import com.portfolio.boardback.dto.request.auth.SignInRequestDto;
import com.portfolio.boardback.dto.request.auth.SignUpRequestDto;
import com.portfolio.boardback.dto.response.auth.SignInResponseDto;
import com.portfolio.boardback.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
  ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
