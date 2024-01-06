package io.dev.authone.repository;

import org.springframework.stereotype.Repository;

import io.dev.authone.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends FeedRepository {

  Optional<UserEntity> findByUsername(String username);

}
