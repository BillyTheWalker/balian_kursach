package com.example.demo.persistense.models;

import com.example.demo.persistense.models.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails
{
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private UserRole userRole;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Collections.singletonList(userRole::toString);
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled()
	{
		return true;
	}

}
