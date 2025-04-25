package com.springOAuthJWTServer.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springOAuthJWTServer.entity.UserInfoEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserInfoConfig implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UserInfoEntity userInfoEntity;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays
				.stream(userInfoEntity
						.getRoles()
						.split(","))
				.map(SimpleGrantedAuthority::new)
				.toList();
	}

	@Override
	public String getPassword() {
		return userInfoEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfoEntity.getEmailId();
	}
	
// These methods are already available in UserDetails interface
	
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
