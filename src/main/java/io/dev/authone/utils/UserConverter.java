package io.dev.authone.utils;

import org.springframework.stereotype.Component;

import io.dev.authone.dto.RegisterReq;
import io.dev.authone.dto.UserRes;
import io.dev.authone.entities.UserEntity;

@Component
public class UserConverter extends GenericConverter<UserEntity, RegisterReq, UserRes> {
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
  public UserRes toResponse(UserEntity entity) {
    UserRes response = UserRes.builder()
      .id(entity.getId())
      .username(entity.getUsername())
      .display(entity.getDisplay())
      .cakeDay(entity.getCakeDay())
      .role(entity.getRole())
      .build();

    return response;
  }
}
