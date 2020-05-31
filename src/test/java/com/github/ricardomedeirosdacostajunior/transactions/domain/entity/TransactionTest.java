package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredField;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.junit.jupiter.api.Test;

public class TransactionTest {

  @Test
  public void transactionClassMustBeAnnotatedWithEntityAnnotation() {
    assertThat(Transaction.class.isAnnotationPresent(Entity.class), is(true));
  }

  @Test
  public void accountMustBeAnnotatedWithManyToOneAnnotation() {
    assertThat(getDeclaredField(Transaction.class, "account").isAnnotationPresent(ManyToOne.class),
        is(true));
  }
}
