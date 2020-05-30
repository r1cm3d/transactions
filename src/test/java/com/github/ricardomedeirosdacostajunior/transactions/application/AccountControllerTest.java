package com.github.ricardomedeirosdacostajunior.transactions.application;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredMethod;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.hasItemInArray;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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
            assertThat(requestMappingAnnotation.path(), hasItemInArray("/accounts")));
  }

  @Test
  public void createMethodMustBeAnnotatedWithPostMappingAnnotation() {
    var postMappingAnnotation = getCreateMethod().getAnnotation(PostMapping.class);

    assertAll(
        () -> assertThat(postMappingAnnotation, is(notNullValue())),
        () ->
            assertThat(postMappingAnnotation.produces(), hasItemInArray(APPLICATION_JSON_VALUE)),
        () -> assertThat(postMappingAnnotation.consumes(), hasItemInArray(APPLICATION_JSON_VALUE)));
  }

  private Method getCreateMethod() {
    return getDeclaredMethod(AccountController.class, "create");
  }
}
