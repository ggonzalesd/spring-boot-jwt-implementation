package io.dev.authone.dto;

import lombok.Getter;

@Getter
public class RegisterReq {
  private String username;
  private String email;
  private String display;
  private String password;
}
