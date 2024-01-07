package io.dev.authone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginReq {
  @NotBlank
  @Pattern(
    regexp = "^[a-z0-9_]{3,}$",
    message = "Username must contain at least 3 characters consisting of lowercase letters, numbers, and underscore"
  )
  private String username;

  @NotBlank
  @Size(min = 8)
  private String password;
}
