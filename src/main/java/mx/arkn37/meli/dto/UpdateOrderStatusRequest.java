package mx.arkn37.meli.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.arkn37.meli.enums.StatusType;

@Getter
@Setter
public class UpdateOrderStatusRequest {

    @NotNull
    private StatusType status;
}
