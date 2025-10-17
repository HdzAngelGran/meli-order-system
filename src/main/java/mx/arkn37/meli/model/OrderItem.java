package mx.arkn37.meli.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Audited
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    private Long orderId;
    private Long productId;
    private Integer quantity;

}
