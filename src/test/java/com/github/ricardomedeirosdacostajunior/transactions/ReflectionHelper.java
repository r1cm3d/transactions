package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import java.lang.reflect.Field;

public class EntityBaseHelper {

  static <T> Field getDeclaredField(Class<T> classType, String name) {
    try {
      return classType.getDeclaredField(name);
    } catch (NoSuchFieldException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
}
