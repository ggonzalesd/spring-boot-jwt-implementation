package io.dev.authone.dto;

import java.util.Date;
//import java.util.UUID;

import io.dev.authone.entities.ERoles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenRes {
  private String token;
  private String username;
  private String email;
  private String display;
  private Date cakeDay;
  private ERoles role;
}
