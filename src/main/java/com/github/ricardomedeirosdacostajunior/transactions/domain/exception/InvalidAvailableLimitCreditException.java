package com.github.ricardomedeirosdacostajunior.transactions.domain.exception;

public class InvalidAvailableLimitCreditException extends ClientErrorException {

  public InvalidAvailableLimitCreditException() {
    super("Available limit credit invalid or not found");
  }
}
