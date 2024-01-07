package io.dev.authone.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.dev.authone.dto.FortressReq;
import io.dev.authone.dto.FortressRes;
import io.dev.authone.entities.FortressEntity;

@Component
public class FortressConverter extends GenericConverter<FortressEntity, FortressReq, FortressRes> {

  @Autowired
  private UserConverter userConverter;

  @Override
  public FortressEntity toEntity(FortressReq request) {
    FortressEntity fortress = FortressEntity.builder()
      .fortname(request.getFortname())
      .isArchived(false)
      .build();
    fortress.setDisplay(request.getDisplay());
    return fortress;
  }

  @Override
  public FortressRes toResponse(FortressEntity entity) {
    FortressRes response = FortressRes.builder()
      .id(entity.getId())
      .fortname(entity.getFortname())
      .display(entity.getDisplay())
      .cakeDay(entity.getCakeDay())
      .isArchived(entity.getIsArchived())
      .owner(
        entity.getOwner() != null ?
        userConverter.toResponse(entity.getOwner()) :
        null
      )
      .build();
    return response;
  }
}