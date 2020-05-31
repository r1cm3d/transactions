package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredField;
import static javax.persistence.EnumType.ORDINAL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.junit.jupiter.api.Test;

public class TransactionTest {

  @Test
  public void transactionClassMustBeAnnotatedWithEntityAnnotation() {
    assertThat(Transaction.class.isAnnotationPresent(Entity.class), is(true));
  }

  @Test
  public void accountMustBeAnnotatedWithManyToOneAnnotation() {
    assertThat(getAccountField().isAnnotationPresent(ManyToOne.class), is(true));
  }

  @Test
  public void accountMustBeAnnotatedWithJoinColumnAnnotation() {
    var joinColumnAnnotation = getAccountField().getAnnotation(JoinColumn.class);

    assertAll(
        "joinColumnAnnotation",
        () -> assertThat(joinColumnAnnotation, is(notNullValue())),
        () -> assertThat(joinColumnAnnotation.name(), is(equalTo("account_id"))));
  }

  @Test
  public void operationTypeMustBeAnnotatedWithEnumeratedAnnotation() {
    var enumeratedAnnotation = getOperationTypeField().getAnnotation(Enumerated.class);

    assertAll(
        "enumeratedAnnotation",
        () -> assertThat(enumeratedAnnotation, is(notNullValue())),
        () -> assertThat(enumeratedAnnotation.value(), is(equalTo(ORDINAL))));
  }

  private Field getAccountField() {
    return getField("account");
  }

  private Field getOperationTypeField() {
    return getField("operationType");
  }

  private Field getField(final String name) {
    return getDeclaredField(Transaction.class, name);
  }
}
