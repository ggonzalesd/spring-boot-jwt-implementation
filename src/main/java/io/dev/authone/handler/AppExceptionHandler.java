package io.dev.authone.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.dev.authone.utils.ResPack;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResPack<Object>> validationBadRequest(MethodArgumentNotValidException e) {

    List<String> errors = e.getBindingResult().getAllErrors().stream()
      .map(err -> "    * " + err.getDefaultMessage())
      .collect(Collectors.toList());

    String errorMessage = "BAD REQUEST:\n" + String.join("\n", errors);

    return ResPack.fail(HttpStatus.BAD_REQUEST, errorMessage);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ResPack<Object>> authUserNotFound(UsernameNotFoundException e) {
    return ResPack.fail(HttpStatus.NOT_FOUND, e.getMessage());
  }

}
