package io.dev.authone.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import io.dev.authone.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

  Optional<UserEntity> findByUsername(String username);

}
