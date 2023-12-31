package com.portfolio.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.portfolio.boardback.dto.response.ResponseDto;
import com.portfolio.boardback.dto.response.user.GetSignInUserResponseDto;
import com.portfolio.boardback.entity.UserEntity;
import com.portfolio.boardback.repository.UserRepository;
import com.portfolio.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

    UserEntity userEntity = null;

    try {

      userEntity = userRepository.findByEmail(email);
      if (userEntity == null) return GetSignInUserResponseDto.notExistUser();

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSignInUserResponseDto.success(userEntity);

  }

}