package io.dev.authone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dev.authone.dto.LoginReq;
import io.dev.authone.dto.RegisterReq;
import io.dev.authone.dto.TokenRes;
import io.dev.authone.entities.ERoles;
import io.dev.authone.entities.UserEntity;
import io.dev.authone.jwt.JwtUtils;
import io.dev.authone.repository.UserRepository;
import io.dev.authone.security.UserPrincipal;
import io.dev.authone.utils.UserConverter;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<TokenRes> login(@RequestBody LoginReq body) {
    UserEntity userEntity = userRepository.findByUsername(body.getUsername())
      .orElseThrow(() -> new UsernameNotFoundException("Usuario '" + body.getUsername() + "' no existe!"));

    if ( passwordEncoder.matches(body.getPassword(), userEntity.getPassword()) ) {
      String token = jwtUtils.generateAccessToken(userEntity.getUsername());
      
      TokenRes response = userConverter.toResponse(userEntity);
      response.setToken(token);

      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<TokenRes> register(@RequestBody RegisterReq body) {
    UserEntity user = userConverter.toEntity(body);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(ERoles.USER);

    userRepository.save(user);

    String token = jwtUtils.generateAccessToken(user.getUsername());

    TokenRes response = userConverter.toResponse(user);
    response.setToken(token);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/verify")
  public ResponseEntity<String> verify(HttpServletRequest request) {
    UserEntity userEntity = UserPrincipal.getCurrentUser();
    return ResponseEntity.ok("Verify is OK! (" + userEntity.getUsername() + ")");
  }

}
