package com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.IN_CASH;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.IN_INSTALLMENTS;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.PAYMENT;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.WITHDRAW;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class OperationTypesEnumerationTest {

  @ParameterizedTest
  @EnumSource(value = OperationTypesEnumeration.class, names = {"IN_CASH", "IN_INSTALLMENTS", "WITHDRAW", "PAYMENT"})
  public void getValueFromOperationTypesIsValid(final OperationTypesEnumeration operationTypesEnumeration) {
    var validOperationTypes = asList(IN_CASH, IN_INSTALLMENTS, WITHDRAW, PAYMENT);

    assertThat(validOperationTypes, hasItem(operationTypesEnumeration));
  }

}
