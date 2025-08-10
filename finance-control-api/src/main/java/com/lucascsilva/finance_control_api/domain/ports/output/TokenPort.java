package com.lucascsilva.finance_control_api.domain.ports.output;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenPort {
  String generateToken(UserDetails userDetails);

  boolean validateToken(String token);
}
