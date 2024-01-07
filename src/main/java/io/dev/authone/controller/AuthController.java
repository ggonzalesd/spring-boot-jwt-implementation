package io.dev.authone.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.dev.authone.utils.ResPack;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<ResPack<TokenRes>> login(@RequestBody @Valid LoginReq body) {
    TokenRes response = authService.login(body);

    return ResPack.success(response);
  }

  @PostMapping("/register")
  public ResponseEntity<ResPack<TokenRes>> register(@RequestBody @Valid RegisterReq body) {
    TokenRes response = authService.register(body);

    return ResPack.success(response);
  }

  @GetMapping("/udpate")
  public ResponseEntity<TokenRes> update() {
    TokenRes response = authService.update();

    return ResponseEntity.ok(response);
  }

}
