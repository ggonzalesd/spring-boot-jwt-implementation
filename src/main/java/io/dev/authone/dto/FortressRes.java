package io.dev.authone.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class FortressRes {
  private UUID id;
  private String fortname;
  private String display;
  @JsonProperty("cake_day")
  private Date cakeDay;
  @JsonProperty("is_archived")
  private Boolean isArchived;
  private UserRes owner;
}
