package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.InvalidAccountException;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

  private final AccountService accountService;

  public TransactionDTO create(@NotNull final TransactionDTO transactionDTO) {
    dtoToEntity(transactionDTO);
    return null;
  }

  private Transaction dtoToEntity(final TransactionDTO transactionDTO) {
    accountService
        .findOptional(transactionDTO.getAccountUuid())
        .orElseThrow(InvalidAccountException::new);

    return null;
  }
}
