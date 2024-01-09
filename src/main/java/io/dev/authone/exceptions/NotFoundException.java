package io.dev.authone.exceptions;

public class NotFoundException extends RuntimeException {

  private String message;

  public NotFoundException(String resource, String identifier) {
    message = resource + " with '" + identifier + "' Was Not FOUND!";
  }

  public static NotFoundException create(String resource, String identifier) {
    return new NotFoundException(resource, identifier);
  }

  @Override
  public String getMessage() {
    return this.message;
  }

}
