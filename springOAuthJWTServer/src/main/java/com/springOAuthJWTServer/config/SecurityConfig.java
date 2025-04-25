package com.springOAuthJWTServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	// Security Setting, to let it access the API using our User
	
	private final UserInfoManagerConfig userManagerConfig;
	
	private final RSAKeyRecord rsaKeyRecord;
	
	@Order(2)
    @Bean
    public SecurityFilterChain signInSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
		log.info("[SecurityConfig.signInSecurityFilterChain - Order(1)]");
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/sign-in/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .userDetailsService(userManagerConfig)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint((request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()));
                })
                .httpBasic(withDefaults())
                .build();
    }
	
	@Order(1)
	@Bean
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		log.info("[SecurityConfig.apiSecurityFilterChain - Order(2)]");
		return httpSecurity
				.securityMatcher(new AntPathRequestMatcher("/api/**"))
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//				.userDetailsService(userManagerConfig)	// To add the below methods for JWT bearer token
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .exceptionHandling(ex -> {
	                 log.error("[SecurityConfig:apiSecurityFilterChain] Exception due to :{}",ex);
	                 ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
	                 ex.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
	             })
//				.formLogin(withDefaults())			// Not used
				.httpBasic(withDefaults())
				.build();
	}
	
	@Order(3)
	@Bean
	public SecurityFilterChain h2ConsoleSecurityFilterChainConfig(HttpSecurity httpSecurity) throws Exception {
		log.info("[SecurityConfig.h2ConsoleSecurityFilterChainConfig - Order(3)]");
		return httpSecurity
				.securityMatcher(new AntPathRequestMatcher(("/h2-console/**")))
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
				//TO DISPLAY THE H2 CONSOLE IN iFrame
				.headers(headers -> headers.frameOptions(withDefaults()).disable())
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(rsaKeyRecord.rsaPublicKey()).build();
	}
	
	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(rsaKeyRecord.rsaPublicKey()).privateKey(rsaKeyRecord.rsaPrivateKey()).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwkSource);
	}
	
	//https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/
	//https://stackoverflow.com/a/76856683
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer()
//	{
//		return (web) -> web.ignoring().requestMatchers("/registerUser", "/", "loginAuthenticate", "/add-flights", "/*/**", "/SarathFlight/**");
//	}
	
	/*
	 * public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity .authorizeHttpRequests((authz) -> authz.anyRequest()
	 * .authenticated() ) .httpBasic(withDefaults()); }
	 */
}
