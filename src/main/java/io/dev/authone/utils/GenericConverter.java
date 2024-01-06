package io.dev.authone.utils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericConverter<Entity, Request, Response> {

  public abstract Response toResponse(Entity entity);
  public abstract Entity toEntity(Request request);

  public List<Response> toResponse(List<Entity> entities) {
    return entities.parallelStream()
      .map(e -> toResponse(e))
      .collect(Collectors.toList());
  }

  public List<Entity> toEntity(List<Request> requests) {
    return requests.parallelStream()
      .map(e -> toEntity(e))
      .collect(Collectors.toList());
  }

}
