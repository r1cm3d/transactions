package com.github.ricardomedeirosdacostajunior.transactions.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class TransactionDTO {

  @JsonProperty("id")
  UUID uuid;
  @JsonProperty("account_id")
  UUID accountUuid;
  @JsonProperty("operation_type")
  Integer operationType;
  BigDecimal amount;
  @JsonProperty("event_date")
  LocalDateTime eventDate;
}
