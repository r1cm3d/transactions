package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.entity.EntityBaseHelper.getDeclaredField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.junit.jupiter.api.Test;

public class AccountTest {

  @Test
  public void accountClassMustBeAnnotatedWithEntityAnnotation() {
    assertThat(Account.class.isAnnotationPresent(Entity.class), is(true));
  }

  @Test
  public void documentNumberMustBeAnnotatedWithColumnAnnotation() {
    var documentNumberColumnAnnotation =
        getDeclaredField(Account.class, "documentNumber").getAnnotation(Column.class);

    assertAll(
        "documentNumberColumnAnnotation",
        () -> assertThat(documentNumberColumnAnnotation, is(notNullValue())),
        () -> assertThat(documentNumberColumnAnnotation.name(), is(equalTo("document_number"))));
  }
}
