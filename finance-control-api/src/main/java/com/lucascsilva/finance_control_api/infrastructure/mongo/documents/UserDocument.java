package com.lucascsilva.finance_control_api.infrastructure.mongo.documents;

import jakarta.validation.constraints.Email;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@EqualsAndHashCode(callSuper = true)
public class UserDocument extends BaseDocument {
  private String name;
  @Email private String email;
  private String password;
  private List<String> roles;
  private Boolean enabled;
}
