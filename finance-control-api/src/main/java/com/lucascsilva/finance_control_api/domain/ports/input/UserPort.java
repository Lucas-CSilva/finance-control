package com.lucascsilva.finance_control_api.domain.ports.input;

import com.lucascsilva.finance_control_api.domain.commands.UpdateUserSettings;
import com.lucascsilva.finance_control_api.domain.models.User;
import reactor.core.publisher.Mono;

public interface UserPort {
  Mono<User> updateSettings(String id, UpdateUserSettings command);
}
