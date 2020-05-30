package com.github.ricardomedeirosdacostajunior.transactions.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@NoArgsConstructor(force = true, access = PROTECTED)
@Getter
@SuperBuilder
public abstract class BaseEntity {

  @Id
  @Column(columnDefinition = "uuid")
  private final UUID uuid;
}
