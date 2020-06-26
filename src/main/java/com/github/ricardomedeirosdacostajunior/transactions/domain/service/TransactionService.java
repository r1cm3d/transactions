package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.valueOf;
import static java.math.BigDecimal.ZERO;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Transaction;
import com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.InsufficientAvailableCreditLimitException;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.InvalidAccountException;
import com.github.ricardomedeirosdacostajunior.transactions.domain.repository.TransactionRepository;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

  private final AccountService accountService;
  private final TransactionRepository transactionRepository;

  public TransactionDTO create(@NotNull final TransactionDTO transactionDTO) {
    return entityToDto(transactionRepository.save(dtoToEntity(transactionDTO)));
  }

  private Transaction dtoToEntity(final TransactionDTO transactionDTO) {
    var account =
        accountService
            .findOptional(transactionDTO.getAccountUuid())
            .orElseThrow(InvalidAccountException::new);
    var operationType = valueOf(transactionDTO.getOperationType());
    var amount = getAmountAccordingOperationType(operationType, transactionDTO.getAmount());
    var newAvailableCreditLimit =
        getNewAvailableCreditLimit(account.getAvailableCreditLimit(), amount);

    accountService.updateAvailableCreditLimit(newAvailableCreditLimit, account);

    return Transaction.builder()
        .uuid(randomUUID())
        .eventDate(now())
        .account(account)
        .operationType(operationType)
        .amount(amount)
        .build();
  }

  private BigDecimal getNewAvailableCreditLimit(
      final BigDecimal oldAvailableCreditLimit, final BigDecimal transactionAmount) {
    var newAvailableCreditLimit = oldAvailableCreditLimit.add(transactionAmount);

    if (newAvailableCreditLimit.compareTo(ZERO) <= 0)
      throw new InsufficientAvailableCreditLimitException();

    return newAvailableCreditLimit;
  }

  private BigDecimal getAmountAccordingOperationType(
      final OperationTypesEnumeration operationTypesEnumeration, final BigDecimal amount) {
    var absoluteAmount = amount.abs();
    return operationTypesEnumeration.isNegative() ? absoluteAmount.negate() : absoluteAmount;
  }

  private TransactionDTO entityToDto(final Transaction transaction) {
    return TransactionDTO.builder()
        .uuid(transaction.getUuid())
        .accountUuid(transaction.getAccount().getUuid())
        .amount(transaction.getAmount())
        .eventDate(transaction.getEventDate())
        .operationType(transaction.getOperationType().getValue())
        .build();
  }
}
