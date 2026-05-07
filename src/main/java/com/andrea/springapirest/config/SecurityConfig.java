package com.andrea.springapirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.andrea.springapirest.security.JwtFilter;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	 private final JwtFilter jwtFilter;
	 
	 public SecurityConfig(JwtFilter jwtFilter) {
	        this.jwtFilter = jwtFilter;
	    }

	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http
	            .cors(cors -> {})
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )

	            .authorizeHttpRequests(auth -> auth

	                // -----------------------
	                // PÚBLICAS
	                // -----------------------
	                .requestMatchers(
	                    "/api/usuarios/auth/**",
	                    "/api/fotos/**",
	                    "/api/apartamentos/public/**"
	                ).permitAll()

	                // -----------------------
	                // SOLO LOGUEADOS
	                // -----------------------
	                .requestMatchers(
	                    "/api/usuarios/me",
	                    "/api/usuarios/change-password",
	                    "/api/usuarios/profile",	                   
	                    "/api/reservas/mis",
	                    "/api/reservas/{id}/**"
	                ).authenticated()
	                // -----------------------
	                // PROPIETARIO
	                // -----------------------
	                .requestMatchers(
		                "/api/propietario/**"
		                	
		                ).hasRole("PROPIETARIO")

	                // -----------------------
	                // EMPLEADO + ADMIN
	                // -----------------------
	                .requestMatchers(
	 	                "/api/usuarios/propietarios/**",
	 	               "/api/apartamentos/**",
	                    "/api/reservas/ingresos/**",
	                    "/api/reservas/all",
	                    "/api/reservas/ocupacion/**",
	                    "/api/precios/**"
	                ).hasAnyRole("EMPLEADO", "ADMIN")

	                // -----------------------
	                // SOLO ADMIN
	                // -----------------------
	                .requestMatchers(
	                	"/api/usuarios/create",
	                	"/api/usuarios/{id}",
	                    "/api/usuarios/empleados/**"
	                    
	                ).hasRole("ADMIN")

	                // -----------------------
	                // TODO LO DEMÁS
	                // -----------------------
	                .anyRequest().authenticated()
	            )

	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	 
	   /*@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http
	            .cors(cors -> {}) 
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                .requestMatchers("/api/usuarios/me").authenticated()
	                .anyRequest().permitAll()
	            )
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


	        return http.build();
	    }*/

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    
	    }

}
