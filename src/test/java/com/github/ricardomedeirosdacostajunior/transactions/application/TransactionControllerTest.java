package com.github.ricardomedeirosdacostajunior.transactions.application;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

  private TransactionDTO aTransactionDTO() {
    return TransactionDTO.builder().build();
  }
}
