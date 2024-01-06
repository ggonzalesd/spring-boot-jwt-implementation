package io.dev.authone.utils;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResPayload <T> {
  private HttpStatus status;
  private String message;
  private T body;
}
