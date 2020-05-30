package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import com.github.ricardomedeirosdacostajunior.transactions.domain.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  private static final String DOCUMENT_NUMBER = "12345678900";
  private static final String UUID = "3554cc7e-ae24-4ab7-b52d-fbfd53644bfe";

  @InjectMocks private AccountService accountService;

  @Mock private AccountRepository accountRepository;

  @Captor private ArgumentCaptor<Account> accountArgumentCaptor;

  private Account account;


  @BeforeEach
  public void setup() {
    account = Account.builder()
        .documentNumber(DOCUMENT_NUMBER)
        .uuid(fromString(UUID))
        .build();
  }

  @Test
  public void create() {
    var accountDTO = AccountDTO.builder().documentNumber(DOCUMENT_NUMBER).build();
    doReturn(account).when(accountRepository).save(any(Account.class));

    var actualAccountDTO = accountService.create(accountDTO);

    verify(accountRepository).save(accountArgumentCaptor.capture());
    var accountCaptured = accountArgumentCaptor.getValue();
    assertAll(
        "accountCaptured",
        () -> assertThat(accountCaptured.getUuid(), is(notNullValue())),
        () -> assertThat(accountCaptured.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER))));
    assertAll(
        "accountDTOReturned",
        () -> assertThat(actualAccountDTO.getUuid(), is(equalTo(fromString(UUID)))),
        () -> assertThat(accountCaptured.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER))));

  }
}
