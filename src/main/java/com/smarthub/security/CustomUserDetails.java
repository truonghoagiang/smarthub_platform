package com.smarthub.security;

import com.smarthub.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !userEntity.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userEntity.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !userEntity.isPasswordExpired();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isEnabled();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
