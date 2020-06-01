package com.github.ricardomedeirosdacostajunior.transactions.application.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.service.AccountService;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public AccountDTO create(@NotNull @RequestBody final AccountDTO accountDTO) {
    return accountService.create(accountDTO);
  }

  @GetMapping(path = "/{uuid}")
  public AccountDTO find(@PathVariable UUID uuid) {
    return accountService.find(uuid);
  }
}
