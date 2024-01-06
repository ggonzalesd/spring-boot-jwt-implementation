package io.dev.authone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dev.authone.dto.FortressReq;
import io.dev.authone.dto.FortressRes;
import io.dev.authone.dto.UserRes;
import io.dev.authone.entities.FortressEntity;
import io.dev.authone.entities.UserEntity;
import io.dev.authone.repository.FortressRepository;
import io.dev.authone.security.UserPrincipal;

@RestController
@RequestMapping("/fortress")
public class ForstressController {

  @Autowired
  private FortressRepository fortressRepository;

  @PostMapping()
  public ResponseEntity<FortressRes> create(@RequestBody FortressReq body) {
    UserEntity userEntity = UserPrincipal.getCurrentUser();

    FortressEntity fortressEntity = FortressEntity.builder()
      .fortname(body.getFortname())
      .isArchived(false)
      .owner(userEntity)
      .build();

    fortressEntity.setDisplay(body.getDisplay());

    fortressRepository.save(fortressEntity);

    FortressRes response = FortressRes.builder()
      .id(fortressEntity.getId())
      .fortname(fortressEntity.getFortname())
      .display(fortressEntity.getDisplay())
      .cakeDay(fortressEntity.getCakeDay())
      .isArchived(fortressEntity.getIsArchived())
      .owner(
        UserRes.builder()
        .id(fortressEntity.getOwner().getId())
        .username(fortressEntity.getOwner().getUsername())
        .display(fortressEntity.getOwner().getDisplay())
        .cakeDay(fortressEntity.getOwner().getCakeDay())
        .role(fortressEntity.getOwner().getRole())
        .build()
      )
      .build();

    return ResponseEntity.ok(response);
  }

}
