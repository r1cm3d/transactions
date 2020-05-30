package com.github.ricardomedeirosdacostajunior.transactions.domain.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccountDTO {

  @NonNull String documentNumber;
  UUID uuid;
}
