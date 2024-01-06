package io.dev.authone.utils;

import org.springframework.stereotype.Component;

import io.dev.authone.dto.RegisterReq;
import io.dev.authone.dto.TokenRes;
import io.dev.authone.entities.UserEntity;

@Component
public class AuthConverter extends GenericConverter<UserEntity, RegisterReq, TokenRes> {
  @Override
  public UserEntity toEntity(RegisterReq request) {
    UserEntity userEntity = UserEntity.builder()
      .username(request.getUsername())
      .email(request.getEmail())
      .password(request.getPassword())
      .build();
    userEntity.setDisplay(request.getDisplay());
    return userEntity;
  }

  @Override
  public TokenRes toResponse(UserEntity entity) {
    return TokenRes.builder()
      .username(entity.getUsername())
      .email(entity.getEmail())
      .display(entity.getDisplay())
      .cakeDay(entity.getCakeDay())
      .role(entity.getRole())
      .build();
  }
}
