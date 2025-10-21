package mx.arkn37.meli.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Audited
@Table(name = "orders")
public class Order extends ExposedEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "status_id")
    private Status status;
    private BigDecimal total;
    private String description;
    private String address;

}
