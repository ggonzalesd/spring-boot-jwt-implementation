package io.dev.authone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dev.authone.dto.LoginReq;
import io.dev.authone.dto.RegisterReq;
import io.dev.authone.dto.TokenRes;
import io.dev.authone.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<TokenRes> login(@RequestBody LoginReq body) {
    TokenRes response = authService.login(body);

    if ( response != null ) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<TokenRes> register(@RequestBody RegisterReq body) {
    TokenRes response = authService.register(body);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/udpate")
  public ResponseEntity<TokenRes> update() {
    TokenRes response = authService.update();

    return ResponseEntity.ok(response);
  }

}
