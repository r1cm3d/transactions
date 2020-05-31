package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.IN_CASH;
import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.PAYMENT;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Transaction;
import com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.InvalidAccountException;
import com.github.ricardomedeirosdacostajunior.transactions.domain.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account invalid or not found";
  private static final UUID ACCOUNT_UUID = fromString("8e9b62a7-fac8-47fc-a4b2-8406e23d85b0");
  private static final UUID TRANSACTION_UUID = fromString("35713a38-48d2-4b26-9dc1-751353d174ad");
  private static final BigDecimal AMOUNT = valueOf(123.45);
  private static final LocalDateTime EVENT_DATE = now();

  @InjectMocks private TransactionService transactionService;

  @Mock private AccountService accountService;
  @Mock private TransactionRepository transactionRepository;

  @Captor private ArgumentCaptor<Transaction> transactionArgumentCaptor;

  private Account account;
  private TransactionDTO expectedNegativeTransactionDTO;
  private TransactionDTO expectedPositiveTransactionDTO;
  private Transaction negativeTransaction;
  private Transaction positiveTransaction;

  @BeforeEach
  public void setup() {
    account = Account.builder().documentNumber("98457968").uuid(ACCOUNT_UUID).build();
    expectedNegativeTransactionDTO = buildTransactionDTO(1, AMOUNT.negate());
    expectedPositiveTransactionDTO = buildTransactionDTO(4, AMOUNT);
    negativeTransaction = buildTransaction(IN_CASH, AMOUNT.negate());
    positiveTransaction = buildTransaction(PAYMENT, AMOUNT);
  }

  @Test
  public void createWhenAccountIsNull() {
    var transactionDTOWithInvalidAccount = TransactionDTO.builder().build();
    doReturn(empty()).when(accountService).findOptional(null);

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
    doReturn(empty()).when(accountService).findOptional(aUUID);

    var invalidAccountException =
        assertThrows(
            InvalidAccountException.class,
            () -> transactionService.create(transactionDTOWithInvalidAccount));

    assertThat(invalidAccountException.getMessage(), is(equalTo(ACCOUNT_NOT_FOUND_MESSAGE)));
  }

  @Test
  public void createWithNegativeValue() {
    var negativeTransactionDTO = buildTransactionDTO(1, AMOUNT.negate());
    doReturn(of(account)).when(accountService).findOptional(ACCOUNT_UUID);
    doReturn(negativeTransaction).when(transactionRepository).save(any(Transaction.class));

    var actualTransactionDTO = transactionService.create(negativeTransactionDTO);

    verify(transactionRepository).save(transactionArgumentCaptor.capture());
    var transactionCaptured = transactionArgumentCaptor.getValue();
    assertAll(
        () -> assertThat(transactionCaptured.getAccount(), is(equalTo(account))),
        () -> assertThat(transactionCaptured.getOperationType(), is(equalTo(IN_CASH))),
        () -> assertThat(transactionCaptured.getAmount(), is(equalTo(AMOUNT.negate()))),
        () -> assertThat(transactionCaptured.getUuid(), is(notNullValue())),
        () -> assertThat(transactionCaptured.getEventDate(), is(notNullValue())));
    assertAll(
        () ->
            assertThat(
                actualTransactionDTO.getUuid(),
                is(equalTo(expectedNegativeTransactionDTO.getUuid()))),
        () ->
            assertThat(
                actualTransactionDTO.getAccountUuid(),
                is(equalTo(expectedNegativeTransactionDTO.getAccountUuid()))),
        () ->
            assertThat(
                actualTransactionDTO.getAmount(),
                is(equalTo(expectedNegativeTransactionDTO.getAmount()))),
        () ->
            assertThat(
                actualTransactionDTO.getOperationType(),
                is(equalTo(expectedNegativeTransactionDTO.getOperationType()))),
        () -> assertThat(actualTransactionDTO.getEventDate(), is(notNullValue())));
  }

  @Test
  public void createWithPositiveValue() {
    var positiveTransactionDTO = buildTransactionDTO(4, AMOUNT);
    doReturn(of(account)).when(accountService).findOptional(ACCOUNT_UUID);
    doReturn(positiveTransaction).when(transactionRepository).save(any(Transaction.class));

    var actualTransactionDTO = transactionService.create(positiveTransactionDTO);

    verify(transactionRepository).save(transactionArgumentCaptor.capture());
    var transactionCaptured = transactionArgumentCaptor.getValue();
    assertAll(
        () -> assertThat(transactionCaptured.getAccount(), is(equalTo(account))),
        () -> assertThat(transactionCaptured.getOperationType(), is(equalTo(PAYMENT))),
        () -> assertThat(transactionCaptured.getAmount(), is(equalTo(AMOUNT))),
        () -> assertThat(transactionCaptured.getUuid(), is(notNullValue())),
        () -> assertThat(transactionCaptured.getEventDate(), is(notNullValue())));
    assertAll(
        () ->
            assertThat(
                actualTransactionDTO.getUuid(),
                is(equalTo(expectedPositiveTransactionDTO.getUuid()))),
        () ->
            assertThat(
                actualTransactionDTO.getAccountUuid(),
                is(equalTo(expectedPositiveTransactionDTO.getAccountUuid()))),
        () ->
            assertThat(
                actualTransactionDTO.getAmount(),
                is(equalTo(expectedPositiveTransactionDTO.getAmount()))),
        () ->
            assertThat(
                actualTransactionDTO.getOperationType(),
                is(equalTo(expectedPositiveTransactionDTO.getOperationType()))),
        () -> assertThat(actualTransactionDTO.getEventDate(), is(notNullValue())));
  }

  private Transaction buildTransaction(
      final OperationTypesEnumeration operationTypesEnumeration, final BigDecimal amount) {
    return Transaction.builder()
        .uuid(TRANSACTION_UUID)
        .operationType(operationTypesEnumeration)
        .account(account)
        .eventDate(EVENT_DATE)
        .amount(amount)
        .build();
  }

  private TransactionDTO buildTransactionDTO(final Integer operationType, final BigDecimal amount) {
    return TransactionDTO.builder()
        .uuid(TRANSACTION_UUID)
        .accountUuid(ACCOUNT_UUID)
        .operationType(operationType)
        .amount(amount)
        .build();
  }
}
