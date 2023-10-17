package org.java.app.auth.conf;

import org.java.app.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class AuthConf {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		
//		FIXME: il ".defaultSuccessUrl("/pizze")" non funziona
			 
			http.authorizeHttpRequests()
				.requestMatchers("/login").permitAll()
		        .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
		        .requestMatchers(new RegexRequestMatcher("/pizze/[0-9]+", null)).hasAnyAuthority("USER", "ADMIN")
		        .requestMatchers("/pizze/**").hasAuthority("ADMIN")/* protegge le rotte e ti fa accedere solo se sei ADMIN*/
		        .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
		        .and().formLogin().defaultSuccessUrl("/pizze")
		        .and().logout();
			
			return http.build();
	}
	
	@Bean
	UserService userDetailsService() {
		return new UserService();
	}
	
    @Bean
    PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
    }

}
