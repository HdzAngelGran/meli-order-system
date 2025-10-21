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

import mx.arkn37.meli.dto.CreateOrderRequest;
import mx.arkn37.meli.dto.OrderResponse;
import mx.arkn37.meli.dto.UpdateOrderRequest;
import mx.arkn37.meli.dto.UpdateOrderStatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    /**
     * Saves a new order to the system.
     *
     * @param createOrderRequest the order to save
     */
    void saveOrder(CreateOrderRequest createOrderRequest);


    /**
     * Updates an existing order.
     *
     * @param orderUuid the unique identifier of the order
     * @param updateOrderRequest the order with updated fields
     */
    void updateOrder(UUID orderUuid, UpdateOrderRequest updateOrderRequest);


    /**
     * Updates the status of an existing order.
     *
     * @param orderUuid the unique identifier of the order
     * @param updateOrderStatusRequest the new status to be assigned
     */
    void updateOrderStatus(UUID orderUuid, UpdateOrderStatusRequest updateOrderStatusRequest);


    /**
     * Retrieves an order by its UUID.
     *
     * @param uuid the unique identifier of the order
     * @return the found order
     */
    OrderResponse orderByUuid(UUID uuid);


    /**
     * Deletes (soft-deletes) an order by its UUID.
     *
     * @param uuid the unique identifier of the order to delete
     */
    void deleteOrder(UUID uuid);


    /**
     * Retrieves a paginated list of all orders.
     *
     * @param pageable the pagination and sorting information
     * @return a page of orders
     */
    Page<OrderResponse> findAllOrders(Pageable pageable);

}