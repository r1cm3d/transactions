package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  public TransactionDTO create(@NotNull final TransactionDTO transactionDTO) {
    return null;
  }
}
