package mx.arkn37.meli.mappers;

import mx.arkn37.meli.dto.StatusResponse;
import mx.arkn37.meli.model.Status;

public class StatusMapper {
  private StatusMapper() {
  }

  public static StatusResponse toResponse(Status status) {
    StatusResponse response = new StatusResponse();
    response.setId(status.getId());
    response.setName(status.getName());
    response.setDescription(status.getDescription());
    response.setCode(status.getCode());
    response.setCreatedAt(status.getCreatedAt());
    response.setUpdatedAt(status.getUpdatedAt());
    response.setDeletedAt(status.getDeletedAt());
    
    return response;
  }
}
