package com.github.ricardomedeirosdacostajunior.transactions.application;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public void create(@Valid @NonNull final AccountDTO accountDTO) {

  }

}
