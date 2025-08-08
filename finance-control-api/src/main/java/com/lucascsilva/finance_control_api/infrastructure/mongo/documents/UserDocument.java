package com.lucascsilva.finance_control_api.infrastructure.mongo.documents;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "users")
@EqualsAndHashCode(callSuper = true)
public class UserDocument extends BaseDocument {
  private String name;
  @Email private String email;
  private String password;
}
