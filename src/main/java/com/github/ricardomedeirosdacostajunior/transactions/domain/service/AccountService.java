package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static java.util.UUID.randomUUID;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import com.github.ricardomedeirosdacostajunior.transactions.domain.repository.AccountRepository;
import lombok.NonNull;

public class AccountService {

  private AccountRepository accountRepository;

  public void create(@NonNull final AccountDTO accountDTO) {
    var account = dtoToEntity(accountDTO);

    accountRepository.save(account);
  }

  private Account dtoToEntity(final AccountDTO accountDTO) {
    return Account.builder()
        .documentNumber(accountDTO.getDocumentNumber())
        .uuid(randomUUID())
        .build();
  }
}
