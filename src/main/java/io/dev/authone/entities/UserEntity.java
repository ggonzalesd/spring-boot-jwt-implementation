package io.dev.authone.entities;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  private UUID id;

  @Column(nullable = false, length = 64, unique = true)
  private String username;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(length = 64)
  private String display;
  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private ERoles role;

  @CreationTimestamp
  @Column(name = "cake_day")
  private Date cakeDay;

}
