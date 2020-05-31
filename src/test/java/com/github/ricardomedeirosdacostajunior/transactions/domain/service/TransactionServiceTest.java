package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static java.util.UUID.fromString;
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

  private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account invalid or not found";

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

    assertThat(invalidAccountException.getMessage(), is(equalTo(ACCOUNT_NOT_FOUND_MESSAGE)));
  }

  @Test
  public void createWhenAccountWasNotFound() {
    var aUUID = fromString("c4682098-9778-4dca-ba45-fe77eed53279");
    var transactionDTOWithInvalidAccount = TransactionDTO.builder().accountUuid(aUUID).build();
    doReturn(null).when(accountService).find(aUUID);

    var invalidAccountException =
        assertThrows(
            InvalidAccountException.class,
            () -> transactionService.create(transactionDTOWithInvalidAccount));

    assertThat(invalidAccountException.getMessage(), is(equalTo(ACCOUNT_NOT_FOUND_MESSAGE)));
  }
}
