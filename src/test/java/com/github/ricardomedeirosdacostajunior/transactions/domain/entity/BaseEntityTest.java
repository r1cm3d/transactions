package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.entity.EntityBaseHelper.getDeclaredField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.junit.jupiter.api.Test;

public class BaseEntityTest {

  private static final String UUID_FIELD = "uuid";

  @Test
  public void entityClassMustBeAnnotatedWithMappedSuperclass() {
    assertThat(BaseEntity.class.isAnnotationPresent(MappedSuperclass.class), is(true));
  }

  @Test
  public void idMustBeAnnotatedWithIdAnnotation() {
    assertThat(getUUUIDField().isAnnotationPresent(Id.class), is(true));
  }

  @Test
  public void idMustBeAnnotatedWithColumnAnnotation() {
    var uuidColumnAnnotation = getUUUIDField().getAnnotation(Column.class);

    assertAll(
        "uuidColumnAnnotation",
        () -> assertThat(uuidColumnAnnotation, is(notNullValue())),
        () -> assertThat(uuidColumnAnnotation.columnDefinition(), is(equalTo(UUID_FIELD))));
  }

  private Field getUUUIDField() {
    return getDeclaredField(BaseEntity.class, UUID_FIELD);
  }
}
