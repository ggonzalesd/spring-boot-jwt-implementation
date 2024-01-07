package io.dev.authone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dev.authone.dto.FortressReq;
import io.dev.authone.dto.FortressRes;
import io.dev.authone.entities.FortressEntity;
import io.dev.authone.entities.UserEntity;
import io.dev.authone.repository.FortressRepository;
import io.dev.authone.security.UserPrincipal;
import io.dev.authone.utils.FortressConverter;

import java.util.List;

@RestController
@RequestMapping("/fortress")
public class ForstressController {

  @Autowired
  private FortressRepository fortressRepository;

  @Autowired
  private FortressConverter fortressConverter;

  @GetMapping
  public ResponseEntity<List<FortressRes>> getAll() {
    UserEntity userEntity = UserPrincipal.getCurrentUser();

    List<FortressRes> response = fortressConverter.toResponse(fortressRepository.findByOwner(userEntity));

    return ResponseEntity.ok(response);
  }

  @PostMapping()
  public ResponseEntity<FortressRes> create(@RequestBody FortressReq body) {
    UserEntity userEntity = UserPrincipal.getCurrentUser();

    FortressEntity fortressEntity = fortressConverter.toEntity(body);
    fortressEntity.setOwner(userEntity);

    fortressRepository.save(fortressEntity);

    FortressRes response = fortressConverter.toResponse(fortressEntity);

    return ResponseEntity.ok(response);
  }

}
