package io.dev.authone.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import io.dev.authone.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

  private UserEntity user;
  private Collection<? extends GrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public static UserPrincipal create(UserEntity user) {
    Collection<GrantedAuthority> authorities = Set.of(
      new SimpleGrantedAuthority(user.getRole().name())
    );
    return new UserPrincipal(user, authorities);
  }

  public static UserEntity getCurrentUser() {
    UserPrincipal principal = (UserPrincipal) SecurityContextHolder
      .getContext().getAuthentication().getPrincipal();
    return principal.getUser();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
