package com.github.ricardomedeirosdacostajunior.transactions.application.handler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ClientErrorHandlerTest {

  @Test
  public void clientErrorClassMustBeAnnotatedWithRestControllerAdvice() {
    assertThat(ClientErrorHandler.class.isAnnotationPresent(RestControllerAdvice.class), is(true));
  }
}
