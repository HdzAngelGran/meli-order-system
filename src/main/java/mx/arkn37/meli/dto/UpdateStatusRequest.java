package mx.arkn37.meli.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusRequest {
  private String name;
  private String description;
  private String code;
}
