package com.github.ricardomedeirosdacostajunior.transactions.domain.dto;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

public class AccountDTOTest {

  @Test
  public void documentNumberFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation = getField("documentNumber").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () ->
            assertThat(jsonPropertyAnnotation.value(), is(equalTo("document_number"))));
  }

  @Test
  public void uuidFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation = getField("uuid").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () ->
            assertThat(jsonPropertyAnnotation.value(), is(equalTo("id"))));
  }

  private Field getField(final String name) {
    return getDeclaredField(AccountDTO.class, name);
  }
}
