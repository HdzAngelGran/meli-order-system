package mx.arkn37.meli.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.arkn37.meli.enums.StatusType;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateOrderRequest {

    @NotNull
    private BigDecimal total;

    private String description;

    @NotBlank
    private String address;

    @NotNull
    private StatusType status;
}
