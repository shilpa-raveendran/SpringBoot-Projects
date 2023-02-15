package ems.projectDemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AppConfig passwordEncoder;

	@Bean
	public UserDetailsManager authenticateUsers() {

		// UserDetails user =
		// User.withUsername("test").password(passwordEncoder.passwordEncoder().encode("password")).roles("EMPLOYEE").build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setAuthoritiesByUsernameQuery("select username,authority from authorities where username=?");
		users.setUsersByUsernameQuery("select username,password,enabled from users where username=?");
		// users.createUser(user);
		return users;
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/employees/showAddForm").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/addEmployee").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/showFormForUpdate").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/saveUpdate").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/deleteEmployee").hasRole("ADMIN").antMatchers("/employees/**")
				.hasRole("EMPLOYEE").antMatchers("/resources/**").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/loginPage").loginProcessingUrl("/authenticateTheUser").permitAll().and()
				.logout().permitAll().and().exceptionHandling().accessDeniedPage("/accessDenied");

		return http.build();
	}

}
