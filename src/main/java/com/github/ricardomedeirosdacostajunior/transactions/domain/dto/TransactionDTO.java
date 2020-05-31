package com.github.ricardomedeirosdacostajunior.transactions.domain.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class TransactionDTO {

  UUID accountUuid;
}
