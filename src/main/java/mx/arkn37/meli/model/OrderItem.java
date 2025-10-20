package mx.arkn37.meli.model;

/**
 * Represents an item within an order, linking products and quantities.
 * <p>
 * Designed for extensibility and integration with inventory, billing, and reporting systems.
 *
 * @author Angel Hernandez
 */

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
