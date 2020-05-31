package com.github.ricardomedeirosdacostajunior.transactions.application;

import static com.github.ricardomedeirosdacostajunior.transactions.ReflectionHelper.getDeclaredMethod;
import static java.util.UUID.fromString;
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
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
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

  private static final UUID aUUID = fromString("dfe579df-d389-4768-9ef6-6bdbbb4818e4");

  @InjectMocks private AccountController accountController;

  @Mock private AccountService accountService;

  private AccountDTO expectedAccountDTO;

  @BeforeEach
  public void setup() {
    expectedAccountDTO = aAccountDTO();
  }

  @Test
  public void accountControllerMustBeAnnotatedWithRestControllerAnnotation() {
    assertThat(AccountController.class.isAnnotationPresent(RestController.class), is(true));
  }

  @Test
  public void accountControllerMustBeAnnotatedWithRequestMappingAnnotation() {
    var requestMappingAnnotation = AccountController.class.getAnnotation(RequestMapping.class);

    assertAll(
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

  @Test
  public void findMethodMustCallAccountServiceFind() {
    doReturn(expectedAccountDTO).when(accountService).find(aUUID);

    var actualAccountDTO = accountController.find(aUUID);

    assertThat(actualAccountDTO, is(equalTo(expectedAccountDTO)));
  }

  private AccountDTO aAccountDTO() {
    return AccountDTO.builder().documentNumber("aDocumentValue").build();
  }

  private Method getCreateMethod() {
    return getMethodByName("create");
  }

  private Method getFindMethod() {
    return getMethodByName("find");
  }

  private Method getMethodByName(final String name) {
    return getDeclaredMethod(AccountController.class, name);
  }
}
