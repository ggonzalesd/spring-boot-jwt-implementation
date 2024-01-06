package io.dev.authone.utils;

import org.springframework.stereotype.Component;

import io.dev.authone.dto.RegisterReq;
import io.dev.authone.dto.TokenRes;
import io.dev.authone.entities.UserEntity;

@Component
public class UserConverter extends GenericConverter<UserEntity, RegisterReq, TokenRes> {
  @Override
  public UserEntity toEntity(RegisterReq request) {
    return UserEntity.builder()
      .username(request.getUsername())
      .email(request.getEmail())
      .display(request.getDisplay())
      .password(request.getPassword())
      .build();
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
