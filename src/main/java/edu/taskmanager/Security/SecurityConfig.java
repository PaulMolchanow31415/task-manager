package edu.taskmanager.Security;

import edu.taskmanager.Model.Role;
import edu.taskmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserRepository repository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			val user = repository.findByEmail(username)
					.orElseThrow(() -> new BadCredentialsException("Пользователь не найден"));
			
			val authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
					.toList();
			
			return UserPrincipal.builder()
					.id(user.getId())
					.email(username)
					.password(user.getPassword())
					.authorities(authorities)
					.build();
		};
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		val provider = new DaoAuthenticationProvider(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());
		return new ProviderManager(provider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher("/**")
				.authorizeHttpRequests(registry -> registry
						.requestMatchers("/user/**").hasAuthority(Role.ADMIN.getAuthority())
						.requestMatchers("/registration", "/register").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(login -> login
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/")
						.permitAll()
				)
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout")
						.permitAll()
				)
				.build();
	}
}

