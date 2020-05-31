package com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationTypesEnumeration {
  IN_CASH(1),
  IN_INSTALLMENTS(2),
  WITHDRAW(3),
  PAYMENT(4);

  private Integer value;
}
