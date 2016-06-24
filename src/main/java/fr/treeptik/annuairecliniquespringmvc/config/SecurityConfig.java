package fr.treeptik.annuairecliniquespringmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.inMemoryAuthentication().withUser("bob").password("bob")
				.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/**").hasRole("USER").anyRequest().authenticated()
				.and()
					.formLogin().loginPage("/login.jsp").usernameParameter("username").loginProcessingUrl("/login")
					.passwordParameter("password").failureUrl("/login.jsp?error=true").permitAll()
				.and()
					.logout().logoutSuccessUrl("/login.jsp?logout=true")
				.and().csrf().disable();

	}

}

























