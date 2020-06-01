package com.github.ricardomedeirosdacostajunior.transactions.application.handler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.ClientErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ClientErrorHandlerTest {

  @Test
  public void clientErrorClassMustBeAnnotatedWithRestControllerAdvice() {
    assertThat(ClientErrorHandler.class.isAnnotationPresent(RestControllerAdvice.class), is(true));
  }

  @Test
  public void clientErrorExceptionMethodMustBeAnnotatedWithExceptionHandlerAnnotation() {
    var clientErrorExceptionMethod =
        ReflectionHelper.getDeclaredMethod(ClientErrorHandler.class, "clientErrorException")
            .getAnnotation(ExceptionHandler.class);

    assertAll(
        () -> assertThat(clientErrorExceptionMethod, is(notNullValue())),
        () ->
            assertThat(
                clientErrorExceptionMethod.value(), hasItemInArray(ClientErrorException.class)));
  }
}
