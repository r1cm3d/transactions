package com.github.ricardomedeirosdacostajunior.transactions.domain.repository;

import static java.math.BigDecimal.valueOf;
import static java.util.UUID.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import java.math.BigDecimal;
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
            .availableCreditLimit(valueOf(5000))
            .build();

    var actualAccount = accountRepository.save(account);

    assertThat(actualAccount, is(notNullValue()));
  }

  @Test
  public void find() {
    var expectedUUID = fromString("f080b248-5989-4fc4-80db-07025922bdc9");
    var expectedDocumentNumber = "06388715907";
    var expectedAvailableCreditLimit = BigDecimal.valueOf(1000.00);

    var actualAccount = accountRepository.findById(expectedUUID).orElseThrow();

    assertAll(
        () -> assertThat(actualAccount.getUuid(), is(equalTo(expectedUUID))),
        () -> assertThat(actualAccount.getDocumentNumber(), is(equalTo(expectedDocumentNumber))),
        () ->
            assertThat(
                actualAccount.getAvailableCreditLimit(),
                is(closeTo(expectedAvailableCreditLimit, valueOf(1)))));
  }

  @Test
  public void findWhenThereIsNoUUID() {
    var nonExistentUUID = fromString("93a82d7a-5f59-4032-8942-a9dc9d8a90ce");

    var actualAccount = accountRepository.findById(nonExistentUUID);

    assertThat(actualAccount.isEmpty(), is(true));
  }
}
