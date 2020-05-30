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

  @Test
  public void entityClassMustBeAnnotatedWithMappedSuperclass() {
    assertThat(BaseEntity.class.isAnnotationPresent(MappedSuperclass.class), is(true));
  }

  @Test
  public void idMustBeAnnotatedWithIdAnnotation() {
    assertThat(getDeclaredField(BaseEntity.class, "uuid").isAnnotationPresent(Id.class), is(true));
  }

  @Test
  public void idMustBeAnnotatedWithColumnAnnotation() {
    var idColumnAnnotation = getUUUIDField().getAnnotation(Column.class);

    assertAll(
        "idColumnAnnotation",
        () -> assertThat(idColumnAnnotation, is(notNullValue())),
        () -> assertThat(idColumnAnnotation.columnDefinition(), is(equalTo("uuid"))));
  }

  private Field getUUUIDField() {
    return getDeclaredField(BaseEntity.class, "uuid");
  }
}
