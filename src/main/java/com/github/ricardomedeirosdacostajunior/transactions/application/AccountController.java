package com.github.ricardomedeirosdacostajunior.transactions.application;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.service.AccountService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {

  private AccountService accountService;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public AccountDTO create(@Valid @NonNull final AccountDTO accountDTO) {
    return accountService.create(accountDTO);
  }
}
