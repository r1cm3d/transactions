package com.github.ricardomedeirosdacostajunior.transactions.application;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredMethod;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.hasItemInArray;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.service.AccountService;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  @InjectMocks private AccountController accountController;

  @Mock private AccountService accountService;

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
        () -> assertThat(requestMappingAnnotation.path(), hasItemInArray("/accounts")),
        () ->
            assertThat(
                requestMappingAnnotation.produces(), hasItemInArray(APPLICATION_JSON_VALUE)));
  }

  @Test
  public void createMethodMustBeAnnotatedWithPostMappingAnnotation() {
    var postMappingAnnotation = getCreateMethod().getAnnotation(PostMapping.class);

    assertAll(
        () -> assertThat(postMappingAnnotation, is(notNullValue())),
        () -> assertThat(postMappingAnnotation.consumes(), hasItemInArray(APPLICATION_JSON_VALUE)));
  }

  @Test
  public void createMethodMustCallAccountServiceCreate() {
    var requestDTO = aAccountDTO();
    var expectedAccountDTO = aAccountDTO();
    doReturn(expectedAccountDTO).when(accountService).create(requestDTO);

    var actualAccountDTO = accountController.create(requestDTO);

    assertThat(actualAccountDTO, is(equalTo(expectedAccountDTO)));
  }

  @Test
  public void findMethodMustBeAnnotatedWithGetMappingAnnotation() {
    var getMappingAnnotation = getFindMethod().getAnnotation(GetMapping.class);

    assertAll(
        () -> assertThat(getMappingAnnotation, is(notNullValue())),
        () -> assertThat(getMappingAnnotation.path(), hasItemInArray("/{uuid}")));
  }

  private AccountDTO aAccountDTO() {
    return AccountDTO.builder().documentNumber("aDocumentValue").build();
  }

  private Method getCreateMethod() {
    return getDeclaredMethod(AccountController.class, "create");
  }

  private Method getFindMethod() {
    return getDeclaredMethod(AccountController.class, "find");
  }
}
