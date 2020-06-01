package com.github.ricardomedeirosdacostajunior.transactions.application.handler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.ClientErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ClientErrorHandlerTest {

  @Test
  public void clientErrorClassMustBeAnnotatedWithRestControllerAdvice() {
    assertThat(ClientErrorHandler.class.isAnnotationPresent(RestControllerAdvice.class), is(true));
  }

  @Test
  public void clientErrorExceptionMethodMustBeAnnotatedWithExceptionHandlerAnnotation() {
    var exceptionHandlerAnnotation =
        ReflectionHelper.getDeclaredMethod(ClientErrorHandler.class, "clientErrorException")
            .getAnnotation(ExceptionHandler.class);

    assertAll(
        () -> assertThat(exceptionHandlerAnnotation, is(notNullValue())),
        () ->
            assertThat(
                exceptionHandlerAnnotation.value(), hasItemInArray(ClientErrorException.class)));
  }

  @Test
  public void clientErrorExceptionMethodMustBeAnnotatedWithResponseStatusAnnotation() {
    var responseStatusAnnotation =
        ReflectionHelper.getDeclaredMethod(ClientErrorHandler.class, "clientErrorException")
            .getAnnotation(ResponseStatus.class);

    assertAll(
        () -> assertThat(responseStatusAnnotation, is(notNullValue())),
        () -> assertThat(responseStatusAnnotation.value(), is(equalTo(BAD_REQUEST))));
  }
}
