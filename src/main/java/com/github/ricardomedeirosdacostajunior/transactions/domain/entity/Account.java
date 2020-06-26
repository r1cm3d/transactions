package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor(force = true, access = PRIVATE)
@Getter
@SuperBuilder
public class Account extends BaseEntity {

  @Column(name = "document_number")
  @NotNull
  private final String documentNumber;

  @Column(name = "available_credit_limit")
  @NotNull
  private final BigDecimal availableCreditLimit;
}
