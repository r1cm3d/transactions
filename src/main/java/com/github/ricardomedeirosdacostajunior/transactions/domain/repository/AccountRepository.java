package com.github.ricardomedeirosdacostajunior.transactions.domain.repository;

import com.github.ricardomedeirosdacostajunior.transactions.domain.entity.Account;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, UUID> {}
