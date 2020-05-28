package com.example.demo.persistense.models;

import com.example.demo.persistense.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor implements UserDetails
{
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Work> works;
	@ManyToOne
	private LoadStandard loadStandard;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Collections.singletonList((GrantedAuthority) UserRole.PROFESSOR::toString);
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
