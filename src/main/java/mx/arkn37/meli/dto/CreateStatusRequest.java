package mx.arkn37.meli.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mx.arkn37.meli.enums.StatusType;

@Getter
@Setter
public class CreateStatusRequest {
  @NotBlank
  private String name;
  private String description;

  @NotNull
  private String code;
}
