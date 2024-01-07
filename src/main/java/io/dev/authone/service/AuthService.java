package io.dev.authone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.dev.authone.dto.LoginReq;
import io.dev.authone.dto.RegisterReq;
import io.dev.authone.dto.TokenRes;
import io.dev.authone.entities.ERoles;
import io.dev.authone.entities.UserEntity;
import io.dev.authone.jwt.JwtUtils;
import io.dev.authone.repository.UserRepository;
import io.dev.authone.security.UserPrincipal;
import io.dev.authone.utils.AuthConverter;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthConverter userConverter;

  @Autowired
  private JwtUtils jwtUtils;

  public TokenRes update() {
    UserEntity userEntity = UserPrincipal.getCurrentUser();

    String token = jwtUtils.generateAccessToken(userEntity.getUsername());

    TokenRes response = userConverter.toResponse(userEntity);
    response.setToken(token);

    return response;
  }

  public TokenRes login(LoginReq body) {
    UserEntity userEntity = userRepository.findByUsername(body.getUsername())
      .orElseThrow(() -> new UsernameNotFoundException("Usuario '" + body.getUsername() + "' no existe!"));

    if ( passwordEncoder.matches(body.getPassword(), userEntity.getPassword()) ) {
      String token = jwtUtils.generateAccessToken(userEntity.getUsername());
      
      TokenRes response = userConverter.toResponse(userEntity);
      response.setToken(token);

      return response;
    } else {
      throw new UsernameNotFoundException("Usuario '" + body.getUsername() + "' no existe!");
    }
  }

  public TokenRes register(RegisterReq body) {
    UserEntity user = userConverter.toEntity(body);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(ERoles.USER);

    userRepository.save(user);

    String token = jwtUtils.generateAccessToken(user.getUsername());

    TokenRes response = userConverter.toResponse(user);
    response.setToken(token);

    return response;
  }

}
