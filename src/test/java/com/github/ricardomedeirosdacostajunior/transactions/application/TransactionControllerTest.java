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

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

  @InjectMocks private TransactionController transactionController;

  @Mock private TransactionService transactionService;

  private TransactionDTO expectedTransactionDTO;

  @BeforeEach
  public void setup() {
    expectedTransactionDTO = aTransactionDTO();
  }

  @Test
  public void transactionControllerMustBeAnnotatedWithRestControllerAnnotation() {
    assertThat(TransactionController.class.isAnnotationPresent(RestController.class), is(true));
  }

  @Test
  public void transactionControllerMustBeAnnotatedWithRequestMappingAnnotation() {
    var requestMappingAnnotation = TransactionController.class.getAnnotation(RequestMapping.class);

    assertAll(
        () -> assertThat(requestMappingAnnotation, is(notNullValue())),
        () -> assertThat(requestMappingAnnotation.path(), hasItemInArray("/transactions")),
        () ->
            assertThat(
                requestMappingAnnotation.produces(), hasItemInArray(APPLICATION_JSON_VALUE)));
  }

  @Test
  public void createMethodMustBeAnnotatedWithPostMappingAnnotation() {
    var postMappingAnnotation =
        getDeclaredMethod(TransactionController.class, "create").getAnnotation(PostMapping.class);

    assertAll(
        () -> assertThat(postMappingAnnotation, is(notNullValue())),
        () -> assertThat(postMappingAnnotation.consumes(), hasItemInArray(APPLICATION_JSON_VALUE)));
  }

  @Test
  public void createMethodMustCallTransactionServiceCreate() {
    var requestDTO = aTransactionDTO();
    doReturn(expectedTransactionDTO).when(transactionService).create(requestDTO);

    var actualAccountDTO = transactionController.create(requestDTO);

    assertThat(actualAccountDTO, is(equalTo(expectedTransactionDTO)));
  }

  private TransactionDTO aTransactionDTO() {
    return TransactionDTO.builder().build();
  }
}
