package com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.IN_CASH;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.IN_INSTALLMENTS;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.PAYMENT;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.WITHDRAW;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OperationTypesEnumerationTest {

  @ParameterizedTest
  @EnumSource(
      value = OperationTypesEnumeration.class,
      names = {"IN_CASH", "IN_INSTALLMENTS", "WITHDRAW", "PAYMENT"})
  public void getValueFromOperationTypesIsValid(
      final OperationTypesEnumeration operationTypesEnumeration) {
    var validOperationTypes = asList(IN_CASH, IN_INSTALLMENTS, WITHDRAW, PAYMENT);

    assertThat(validOperationTypes, hasItem(operationTypesEnumeration));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4})
  public void valueOf(int value) {
    var operationType = OperationTypesEnumeration.valueOf(value);

    assertThat(operationType, is(notNullValue()));
  }
}
