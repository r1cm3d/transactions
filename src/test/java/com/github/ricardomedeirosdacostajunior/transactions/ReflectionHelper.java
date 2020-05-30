package com.github.ricardomedeirosdacostajunior.transactions;

import java.lang.reflect.Field;

public class ReflectionHelper {

  public static <T> Field getDeclaredField(Class<T> classType, String name) {
    try {
      return classType.getDeclaredField(name);
    } catch (NoSuchFieldException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
}
