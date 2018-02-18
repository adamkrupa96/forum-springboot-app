package pl.krupa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pl.krupa.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserService userService;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(
					"/",
					"/rejestracja",
					"/aktualnosci/**",
					"**/static").permitAll()
				.antMatchers("/panelAdmina/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/panelAdmina/dodajArtykul").hasAnyAuthority("ROLE_MENAGER")
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/zaloguj").permitAll()
			.and()
				.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/wyloguj"))
	                .logoutSuccessUrl("/zaloguj?wylogowano")
				.permitAll();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
    	authBuilder.authenticationProvider(authenticationProvider());
    }
}
