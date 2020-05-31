package com.github.ricardomedeirosdacostajunior.transactions.domain.repository;

import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Transaction;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {}
