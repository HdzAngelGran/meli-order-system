package mx.arkn37.meli.model;

/**
 * Represents a product available for ordering.
 * <p>
 * Integrates with catalog, inventory, and digital commerce tools.
 *
 * @author Angel Hernandez
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Audited
@Table(name = "products")
public class Product extends ExposedEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private String brand;
    private String model;
    private String color;
    private String size;
    private boolean isEnabled;

}
