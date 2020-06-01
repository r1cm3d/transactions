package com.github.ricardomedeirosdacostajunior.transactions.domain.service;

import static java.util.Optional.ofNullable;
import static java.util.UUID.randomUUID;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import com.github.ricardomedeirosdacostajunior.transactions.domain.exception.InvalidAccountException;
import com.github.ricardomedeirosdacostajunior.transactions.domain.repository.AccountRepository;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountDTO create(@NotNull final AccountDTO accountDTO) {
    var account = dtoToEntity(accountDTO);

    return entityToDto(accountRepository.save(account));
  }

  public AccountDTO find(@NotNull final UUID uuid) {
    return findOptional(uuid).map(this::entityToDto).orElse(null);
  }

  public Optional<Account> findOptional(@NotNull final UUID uuid) {
    return accountRepository.findById(uuid);
  }

  private Account dtoToEntity(final AccountDTO accountDTO) {
    var documentNumber = ofNullable(accountDTO)
        .map(AccountDTO::getDocumentNumber)
        .orElseThrow(InvalidAccountException::new);

    return Account.builder()
        .documentNumber(documentNumber)
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
