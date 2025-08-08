package com.lucascsilva.finance_control_api.infrastructure.mongo.documents;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDocument {
  @Id protected String id;

  @CreatedDate
  @Field("created_at")
  protected Instant createdAt;

  @LastModifiedDate
  @Field("updated_at")
  protected Instant updatedAt;
}
