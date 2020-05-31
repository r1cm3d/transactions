package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static javax.persistence.EnumType.ORDINAL;
import static lombok.AccessLevel.PRIVATE;

import com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(force = true, access = PRIVATE)
public final class Transaction extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "account_id")
  private final Account account;

  @Enumerated(ORDINAL)
  private final OperationTypesEnumeration operationType;
}
