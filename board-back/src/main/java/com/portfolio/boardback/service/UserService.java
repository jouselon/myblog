package com.portfolio.boardback.service;

import com.portfolio.boardback.dto.response.user.GetSignInUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

}
