package com.github.ricardomedeirosdacostajunior.transactions.domain.dto;

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

  UUID uuid;
  UUID accountUuid;
  Integer operationType;
  BigDecimal amount;
  LocalDateTime eventDate;
}
