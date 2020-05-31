package com.github.ricardomedeirosdacostajunior.transactions.domain.repository;

import static com.github.ricardomedeirosdacostajunior.transactions.domain.enumeration.OperationTypesEnumeration.PAYMENT;
import static java.math.BigDecimal.TEN;
import static java.time.LocalDateTime.now;
import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TransactionRepositoryIT {

  @Autowired private TransactionRepository transactionRepository;

  @Test
  public void save() {
    var transaction =
        Transaction.builder()
            .uuid(fromString("d1e59bc2-4246-42ea-b7f0-7e893db3d406"))
            .account(
                Account.builder().uuid(fromString("f080b248-5989-4fc4-80db-07025922bdc9")).build())
            .eventDate(now())
            .operationType(PAYMENT)
            .amount(TEN)
            .build();

    var actualTransaction = transactionRepository.save(transaction);

    assertThat(actualTransaction, is(notNullValue()));
  }
}
