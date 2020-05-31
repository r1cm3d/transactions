package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static java.util.UUID.randomUUID;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import com.github.ricardomedeirosdacostajunior.transactions.domain.repository.AccountRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountDTO create(@NonNull final AccountDTO accountDTO) {
    var account = dtoToEntity(accountDTO);

    return entityToDto(accountRepository.save(account));
  }

  private Account dtoToEntity(final AccountDTO accountDTO) {
    return Account.builder()
        .documentNumber(accountDTO.getDocumentNumber())
        .uuid(randomUUID())
        .build();
  }

  private AccountDTO entityToDto(final Account account) {
    return AccountDTO.builder()
        .documentNumber(account.getDocumentNumber())
        .uuid(account.getUuid())
        .build();
  }
}
