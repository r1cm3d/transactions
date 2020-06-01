package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public final class InvalidAccountException extends ClientErrorException {

  public InvalidAccountException() {
    super("Account invalid or not found");
  }
}
