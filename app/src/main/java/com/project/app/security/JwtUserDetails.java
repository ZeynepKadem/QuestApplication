package com.project.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.app.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails{

	public Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static JwtUserDetails create(User user) {
		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority("user"));
		return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authoritiesList);
		
	}
	@Override
	public boolean isAccountNonExpired() {
		//hesabın süresi dolmamış
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//hesap kilitli değil
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//kimlik bilgilerinin süresi dolmamış
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		//etkin
		
		return true;
	}



}
