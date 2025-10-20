package mx.arkn37.meli.service;

/**
 * Service interface for order operations.
 * <p>
 * Defines business logic and integration points for order management.
 * <ul>
 *   <li>Promotes separation of concerns and testability.</li>
 *   <li>Stakeholders: backend developers, QA, integrators.</li>
 * </ul>
 *
 * @author Angel Hernandez
 */

import mx.arkn37.meli.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    void saveOrder(Order order);

    /**
     * Saves a new order to the system.
     *
     * @param order the order to save
     */

    void updateOrder(Order order);

    /**
     * Updates an existing order.
     *
     * @param order the order with updated fields
     */

    Order orderByUuid(UUID uuid);

    /**
     * Retrieves an order by its UUID.
     *
     * @param uuid the unique identifier of the order
     * @return the found order
     */

    void deleteOrder(UUID uuid);

    /**
     * Deletes (soft-deletes) an order by its UUID.
     *
     * @param uuid the unique identifier of the order to delete
     */

    Page<Order> findAllOrders(Pageable pageable);
    /**
     * Retrieves a paginated list of all orders.
     *
     * @param pageable the pagination and sorting information
     * @return a page of orders
     */

}