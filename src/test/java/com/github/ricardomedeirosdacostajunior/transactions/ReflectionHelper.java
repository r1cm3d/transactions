package com.github.ricardomedeirosdacostajunior.transactions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class ReflectionHelper {

  public static <T> Field getDeclaredField(final Class<T> classType, final String name) {
    try {
      return classType.getDeclaredField(name);
    } catch (NoSuchFieldException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public static <T> Method getDeclaredMethod(final Class<T> classType, final String name) {
    return Stream.of(classType.getDeclaredMethods())
        .filter(x -> x.getName().equals(name))
        .findFirst()
        .orElseThrow(RuntimeException::new);
  }
}
