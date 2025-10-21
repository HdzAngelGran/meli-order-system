package mx.arkn37.meli.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateOrderRequest {

  private BigDecimal total;
  private String description;
  private String address;

}
