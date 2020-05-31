package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.persistence.Entity;
import org.junit.jupiter.api.Test;

public class TransactionTest {

  @Test
  public void transactionClassMustBeAnnotatedWithEntityAnnotation() {
    assertThat(Transaction.class.isAnnotationPresent(Entity.class), is(true));
  }
}
