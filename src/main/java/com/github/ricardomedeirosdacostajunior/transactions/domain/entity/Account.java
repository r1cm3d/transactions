package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor(force = true, access = PRIVATE)
@Getter
@SuperBuilder
public class Account extends BaseEntity {

  @Column(name = "document_number")
  @NotNull
  private final String documentNumber;
}
