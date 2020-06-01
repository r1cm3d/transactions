package com.github.ricardomedeirosdacostajunior.transactions.domain.dto;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

public class AccountDTOTest {

  @Test
  public void documentNumberFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation =
        getDeclaredField(AccountDTO.class, "documentNumber").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () ->
            assertThat(jsonPropertyAnnotation.value(), is(equalTo("document_number"))));
  }

  @Test
  public void uuidFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation =
        getDeclaredField(AccountDTO.class, "uuid").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () ->
            assertThat(jsonPropertyAnnotation.value(), is(equalTo("id"))));
  }
}
