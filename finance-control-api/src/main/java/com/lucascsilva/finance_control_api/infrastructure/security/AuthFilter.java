package com.lucascsilva.finance_control_api.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthFilter implements WebFilter {

  private final TokenService tokenService;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return chain.filter(exchange);
    }

    String token = authHeader.substring(7);
    String email = tokenService.extractUsername(token);

    if (email == null) {
      return chain.filter(exchange);
    }

    Mono<Authentication> authenticationMono =
        ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .switchIfEmpty(
                Mono.defer(
                    () ->
                        userDetailsService
                            .findByUsername(email)
                            .filter(userDetails -> tokenService.validateToken(token))
                            .map(
                                userDetails ->
                                    new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities()))));

    return authenticationMono
        .flatMap(
            authentication ->
                chain
                    .filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication)))
        .switchIfEmpty(chain.filter(exchange));
  }
}
