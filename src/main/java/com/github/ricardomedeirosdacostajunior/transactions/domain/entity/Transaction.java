package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(force = true, access = PRIVATE)
public final class Transaction extends BaseEntity {

  @ManyToOne
  private final Account account;

//      () -> assertThat(transactionCaptured.getAccount(), is(equalTo(account))),
//      () -> assertThat(transactionCaptured.getOperationType(), is(equalTo(IN_CASH))),
//      () -> assertThat(transactionCaptured.getAmount(), is(equalTo(AMOUNT.negate()))),
//      () -> assertThat(transactionCaptured.getEventDate(), is(notNullValue())),
//      () -> assertThat(transactionCaptured.getUuid(), is(equalTo(actualTransactionDTO.getAccountUuid()))),
//      () -> assertThat(transactionCaptured.getEventDate(), is(equalTo(actualTransactionDTO.getEventDate()))));

}
