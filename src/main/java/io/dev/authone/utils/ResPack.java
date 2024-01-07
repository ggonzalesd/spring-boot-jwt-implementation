package io.dev.authone.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResPack<T> {
  private boolean ok;
  private String message;
  private T body;

  public ResponseEntity<ResPack<T>> response() {
    return new ResponseEntity<>(this, HttpStatus.OK);
  }

  public ResponseEntity<ResPack<T>> response(HttpStatus status) {
    return new ResponseEntity<>(this, status);
  }

  public static <T> ResponseEntity<ResPack<T>> success(T body) {
    return ResponseEntity.ok(
      new ResPack<T>(true, "success", body)
    );
  }

  public static <T> ResponseEntity<ResPack<T>> fail(HttpStatus status, String message) {
    return ResponseEntity.status(status).body(
      new ResPack<T>(false, message, null)
    );
  }

}
