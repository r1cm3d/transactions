package com.github.ricardomedeirosdacostajunior.transactions.domain.repository;

import static java.util.UUID.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AccountRepositoryIT {

  @Autowired private AccountRepository accountRepository;

  @Test
  public void save() {
    var account =
        Account.builder()
            .uuid(fromString("d1e59bc2-4246-42ea-b7f0-7e893db3d406"))
            .documentNumber("666")
            .build();

    var actualAccount = accountRepository.save(account);

    assertThat(actualAccount, is(notNullValue()));
  }
}
