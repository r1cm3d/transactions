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

public class TransactionDTOTest {

  @Test
  public void uuidFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation = getField("uuid").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () -> assertThat(jsonPropertyAnnotation.value(), is(equalTo("id"))));
  }

  @Test
  public void accountUuidFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation = getField("accountUuid").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () -> assertThat(jsonPropertyAnnotation.value(), is(equalTo("account_id"))));
  }

  @Test
  public void operationTypeFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation = getField("operationType").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () -> assertThat(jsonPropertyAnnotation.value(), is(equalTo("operation_type"))));
  }

  @Test
  public void eventDateFieldMustBeAnnotatedWithJsonPropertyAnnotation() {
    var jsonPropertyAnnotation = getField("eventDate").getAnnotation(JsonProperty.class);

    assertAll(
        () -> assertThat(jsonPropertyAnnotation, is(notNullValue())),
        () -> assertThat(jsonPropertyAnnotation.value(), is(equalTo("event_date"))));
  }

  private Field getField(final String name) {
    return getDeclaredField(TransactionDTO.class, name);
  }
}
