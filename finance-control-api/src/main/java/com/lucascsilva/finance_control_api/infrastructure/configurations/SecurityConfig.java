package com.lucascsilva.finance_control_api.infrastructure.configurations;

import com.lucascsilva.finance_control_api.infrastructure.security.AuthFilter;
import com.lucascsilva.finance_control_api.infrastructure.security.AuthenticationManager;
import com.lucascsilva.finance_control_api.infrastructure.security.SecurityContextRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final AuthFilter authFilter;
  private final SecurityContextRepository securityContextRepository;
  private final AuthenticationManager authenticationManager;

  @Value("${cors.allowed-origins}")
  private List<String> allowedOrigins;

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(allowedOrigins);
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http.cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authenticationManager(authenticationManager)
        .securityContextRepository(securityContextRepository)
        .authorizeExchange(
            exchanges ->
                exchanges.pathMatchers("/auth/**").permitAll().anyExchange().authenticated())
        .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        .build();
  }
}
