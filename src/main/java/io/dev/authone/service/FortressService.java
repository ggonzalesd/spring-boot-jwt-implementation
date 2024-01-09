package io.dev.authone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dev.authone.dto.FortressReq;
import io.dev.authone.entities.FortressEntity;
import io.dev.authone.entities.UserEntity;
import io.dev.authone.repository.FortressRepository;
import io.dev.authone.security.UserPrincipal;
import io.dev.authone.utils.FortressConverter;

import java.util.List;

@Service
public class FortressService {
  @Autowired
  private FortressRepository fortressRepository;

  @Autowired
  private FortressConverter fortressConverter;

  public List<FortressEntity> getAll() {
    UserEntity userEntity = UserPrincipal.getCurrentUser();

    List<FortressEntity> fortresses = fortressRepository.findByOwner(userEntity);

    return fortresses;
  }

  public FortressEntity create(FortressReq body) {
    UserEntity userEntity = UserPrincipal.getCurrentUser();

    FortressEntity fortressEntity = fortressConverter.toEntity(body);
    fortressEntity.setOwner(userEntity);

    fortressRepository.save(fortressEntity);

    return fortressEntity;
  }

}
