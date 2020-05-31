package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.InvalidAccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @InjectMocks private TransactionService transactionService;

  @Mock private AccountService accountService;

  @Test
  public void createWhenAccountIsNull() {
    var transactionDTOWithInvalidAccount = TransactionDTO.builder().build();
    doReturn(null).when(accountService).find(null);

    var invalidAccountException =
        assertThrows(
            InvalidAccountException.class,
            () -> transactionService.create(transactionDTOWithInvalidAccount));

    assertThat(invalidAccountException.getMessage(), is(equalTo("Account invalid or not found")));
  }
}
