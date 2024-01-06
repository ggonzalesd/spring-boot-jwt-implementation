package io.dev.authone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

import io.dev.authone.entities.FeedEntity;

@NoRepositoryBean
public interface FeedRepository extends CrudRepository<FeedEntity, UUID>{

}
