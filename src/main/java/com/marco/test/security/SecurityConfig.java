package com.marco.test.security;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	
	@Bean
	UserDetailsService userDetailService() {
		
		UserDetails user = User.builder()
				.username("marco")
				.password(passwordEncoder().encode("pass"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filetrChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(
                        PathRequest
                                .toStaticResources()
                                .atCommonLocations()
                ).permitAll() 
                .anyRequest().authenticated()
                .and()

                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout(logout -> logout.logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"));
					return http.build();
	}

}
