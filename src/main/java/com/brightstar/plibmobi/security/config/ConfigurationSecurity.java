package com.brightstar.plibmobi.security.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.brightstar.plibmobi.security.custom.CustomAuthenticationProvider;
import com.brightstar.plibmobi.security.custom.MyUserDetailsService;

@Configuration
@ComponentScan(basePackages = { "com.brightstar.plibmobi.security" })
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
            @Override
            protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
                WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
                root.setDefaultRolePrefix(""); //remove the prefix ROLE_
                return root;
            }
        });
    }
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/webjars/**", "/static/**", "/css/**", "/js/**", "/img/**", "/test/**")
					.permitAll()					
					.antMatchers("/dashboard").hasRole("ADMIN")
					.antMatchers("/usuario").hasRole("EDIT")
					.anyRequest()
					.authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=1")
					.permitAll()
			.and()
				.logout()
				 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Configuracao necessaria para logout via GET
					.logoutSuccessUrl("/login?logout")
						.permitAll()
			.and()
	            .exceptionHandling().accessDeniedPage("/timeout")
	            
	        .and()
	        	.sessionManagement()
	        	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	        		.maximumSessions(1).expiredUrl("/login?logout").and().sessionFixation().migrateSession();	
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return getMd5(charSequence.toString());
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return getMd5(charSequence.toString()).equals(s);
			}
		};
	}
	
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown" + " for incorrect algorithm: " + e);
			return null;
		}
	}

}
