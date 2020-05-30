package com.github.ricardomedeirosdacostajunior.transactions.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AccountControllerTest {

  @Test
  public void accountControllerMustBeAnnotatedWithRestControllerAnnotation() {
    assertThat(AccountController.class.isAnnotationPresent(RestController.class), is(true));
  }

  @Test
  public void accountControllerMustBeAnnotatedWithRequestMappingAnnotation() {
    var requestMappingAnnotation = AccountController.class.getAnnotation(RequestMapping.class);

    assertAll(
        "requestMappingAnnotation",
        () -> assertThat(requestMappingAnnotation, is(notNullValue())),
        () ->
            assertThat(requestMappingAnnotation.path()[0], is(equalTo("/accounts"))),
        () -> assertThat(requestMappingAnnotation.produces()[0], is(equalTo(APPLICATION_JSON_VALUE))));
  }
}
