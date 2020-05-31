package com.example.demo.config;

import com.example.demo.persistense.models.LoadStandard;
import com.example.demo.persistense.models.Property;
import com.example.demo.persistense.models.User;
import com.example.demo.persistense.models.enums.UserRole;
import com.example.demo.persistense.repository.LoadStandardRepository;
import com.example.demo.persistense.repository.UserRepository;
import com.example.demo.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SetUpDB
{
	private final PropertyService propertyService;
	private final UserRepository userRepository;
	private final LoadStandardRepository loadStandardRepository;
	private final PasswordEncoder passwordEncoder;

	public void initDB()
	{
		setProperties();
		saveAdmin();
		setLoadStandarts();
	}

	private void saveAdmin()
	{
		try
		{
			userRepository.findByUsername("admin").orElseGet(() -> userRepository
					.save(User.builder()
							.userRole(UserRole.ADMIN)
							.username("admin")
							.password(passwordEncoder.encode("admin"))
							.build()));
			userRepository.findByUsername("edu_part").orElseGet(() -> userRepository
					.save(User.builder()
							.userRole(UserRole.CHASTYNA)
							.username("edu_part")
							.password(passwordEncoder.encode("edu_part"))
							.build()));
		}
		catch (Exception e)
		{
		}
	}

	private void setProperties()
	{
		try
		{
			propertyService.save(Property.builder().name("academic.group.students").value(String.valueOf(30)).build());
		}
		catch (Exception e)
		{
		}
	}

	private void setLoadStandarts()
	{
		try
		{
			loadStandardRepository.save(LoadStandard.builder().name("Педагогічний працівник").load(720).build());
			loadStandardRepository.save(LoadStandard.builder().name("Асистент").load(600).build());
			loadStandardRepository.save(LoadStandard.builder().name("Викладач").load(600).build());
			loadStandardRepository.save(LoadStandard.builder().name("Старший викладач").load(600).build());
			loadStandardRepository.save(LoadStandard.builder().name("Доцент, кандидат наук").load(600).build());
			loadStandardRepository.save(LoadStandard.builder().name("Професор, доктор наук").load(550).build());
			loadStandardRepository.save(LoadStandard.builder().name("Завідувач кафедри, професор").load(500).build());
			loadStandardRepository.save(LoadStandard.builder().name("Доцент").load(450).build());
		}
		catch (Exception e)
		{
		}
	}
}
