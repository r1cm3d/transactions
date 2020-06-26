package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.junit.jupiter.api.Test;

public class AccountTest {

  private static final String DOCUMENT_NUMBER_COLUMN = "document_number";
  private static final String DOCUMENT_NUMBER_ATTRIBUTE = "documentNumber";
  private static final String AVAILABLE_CREDIT_LIMIT_ATTRIBUTE = "availableCreditLimit";
  private static final String AVAILABLE_CREDIT_LIMIT_COLUMN = "available_credit_limit";

  @Test
  public void accountClassMustBeAnnotatedWithEntityAnnotation() {
    assertThat(Account.class.isAnnotationPresent(Entity.class), is(true));
  }

  @Test
  public void documentNumberMustBeAnnotatedWithColumnAnnotation() {
    var documentNumberColumnAnnotation =
        getDeclaredField(Account.class, DOCUMENT_NUMBER_ATTRIBUTE).getAnnotation(Column.class);

    assertAll(
        () -> assertThat(documentNumberColumnAnnotation, is(notNullValue())),
        () ->
            assertThat(documentNumberColumnAnnotation.name(), is(equalTo(DOCUMENT_NUMBER_COLUMN))));
  }

  @Test
  public void availableCreditLimitMustBeAnnotatedWithColumnAnnotation() {
    var availableCreditLimitColumnAnnotation =
        getDeclaredField(Account.class, AVAILABLE_CREDIT_LIMIT_ATTRIBUTE)
            .getAnnotation(Column.class);

    assertAll(
        () -> assertThat(availableCreditLimitColumnAnnotation, is(notNullValue())),
        () ->
            assertThat(
                availableCreditLimitColumnAnnotation.name(),
                is(equalTo(AVAILABLE_CREDIT_LIMIT_COLUMN))));
  }
}
