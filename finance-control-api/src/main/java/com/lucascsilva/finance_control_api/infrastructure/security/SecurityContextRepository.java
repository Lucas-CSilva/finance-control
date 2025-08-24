package com.lucascsilva.finance_control_api.infrastructure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

  private static final String TOKEN_PREFIX = "Bearer ";
  private final TokenService tokenService;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
    return Mono.empty();
  }

  @Override
  public Mono<SecurityContext> load(ServerWebExchange exchange) {
    return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
        .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX))
        .map(authHeader -> authHeader.substring(TOKEN_PREFIX.length()))
        .flatMap(this::extractUsernameFromToken)
        .flatMap(userDetailsService::findByUsername)
        .flatMap(this::createSecurityContext);
  }

  private Mono<String> extractUsernameFromToken(String token) {
    if (!tokenService.validateToken(token)) {
      return Mono.empty();
    }
    String username = tokenService.extractUsername(token);
    return Mono.just(username);
  }

  private Mono<SecurityContext> createSecurityContext(UserDetails userDetails) {
    var authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContext context = new SecurityContextImpl(authentication);
    return Mono.just(context);
  }
}
