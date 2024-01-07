package io.dev.authone.repository;

import org.springframework.stereotype.Repository;

import io.dev.authone.entities.FortressEntity;
import io.dev.authone.entities.UserEntity;

import java.util.List;

@Repository
public interface FortressRepository extends FeedRepository {

  List<FortressEntity> findByOwner(UserEntity owner);

}
