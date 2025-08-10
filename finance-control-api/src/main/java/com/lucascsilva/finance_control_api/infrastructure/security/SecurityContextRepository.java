package com.lucascsilva.finance_control_api.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

  private static final String TOKEN_PREFIX = "Bearer ";
  private final TokenService tokenService;

  @Override
  public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
    return Mono.empty();
  }

  @Override
  public Mono<SecurityContext> load(ServerWebExchange exchange) {
    return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
        .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX))
        .map(authHeader -> authHeader.substring(TOKEN_PREFIX.length()))
        .flatMap(
            token -> {
              if (!tokenService.validateToken(token)) {
                return Mono.empty();
              }

              String username = tokenService.extractUsername(token);
              var authorities =
                  tokenService.extractRoles(token).stream()
                      .map(SimpleGrantedAuthority::new)
                      .toList();

              var authentication =
                  new UsernamePasswordAuthenticationToken(username, null, authorities);
              SecurityContext context = new SecurityContextImpl(authentication);
              return Mono.just(context);
            });
  }
}
