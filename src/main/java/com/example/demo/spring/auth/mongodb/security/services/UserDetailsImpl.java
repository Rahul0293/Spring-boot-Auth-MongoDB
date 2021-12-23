package com.example.demo.spring.auth.mongodb.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.spring.auth.mongodb.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserDetailsImpl implements UserDetails{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String username;
	private String email;
	
	@JsonIgnoreProperties
	private String password;
	

	public Collection<? extends GrantedAuthority> authorities;
	
	
	public UserDetailsImpl(String id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id=id;
		this.username=username;
		this.email=email;
		this.password=password;
		this.authorities=authorities;
	}
	
	public static UserDetailsImpl build (User user) {
		List<GrantedAuthority> authorities =user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return authorities;
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean equals(Object o) {
		if (this==o)
			return true;
		if(o==null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user=(UserDetailsImpl) o;
		return Objects.deepEquals(id, user.id);
	}

}
