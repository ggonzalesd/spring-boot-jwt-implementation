package io.dev.authone.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dev.authone.entities.ERoles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserRes {
  private UUID id;
  private String username;
  private String display;
  @JsonProperty("cake_day")
  private Date cakeDay;
  private ERoles role;
}
