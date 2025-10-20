package mx.arkn37.meli.controller;

/**
 * REST controller for managing orders.
 * <p>
 * Provides endpoints for CRUD operations and pagination, supporting integration with frontend, mobile, and partner APIs.
 * <ul>
 *   <li>Promotes digital tool usage via clear RESTful design.</li>
 *   <li>Stakeholders: developers, integrators, and business analysts.</li>
 *   <li>Extensible for future endpoints and business logic.</li>
 * </ul>
 *
 * @author Angel Hernandez
 */

import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.model.Order;
import mx.arkn37.meli.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates a new order.
     *
     * @param order the order to create, validated and mapped from the request body
     * @return HTTP 201 with confirmation message if successful
     *
     *         <b>Integration:</b> Used by frontend, mobile, and partner APIs to
     *         create orders.
     *         <b>Stakeholders:</b> Developers, integrators, business analysts.
     */
    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }

    /**
     * Updates an existing order.
     *
     * @param order the order with updated fields, mapped from the request body
     * @return HTTP 200 with confirmation message if successful
     *
     *         <b>Integration:</b> Enables order modification via digital tools and
     *         APIs.
     *         <b>Stakeholders:</b> Developers, QA, business users.
     */
    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return new ResponseEntity<>("Order updated", HttpStatus.OK);
    }

    /**
     * Retrieves an order by its UUID.
     *
     * @param uuid the unique identifier of the order
     * @return HTTP 200 with the order if found
     *
     *         <b>Integration:</b> Used by client apps and partners to fetch order
     *         details.
     *         <b>Stakeholders:</b> Developers, support, business analysts.
     */
    @GetMapping("{uuid}")
    public ResponseEntity<Order> getOrderByUuid(@PathVariable UUID uuid) {
        Order order = orderService.orderByUuid(uuid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * Deletes (soft-deletes) an order by its UUID.
     *
     * @param uuid the unique identifier of the order to delete
     * @return HTTP 200 with confirmation message if successful
     *
     *         <b>Integration:</b> Supports order lifecycle management in digital
     *         platforms.
     *         <b>Stakeholders:</b> Developers, business users, auditors.
     */
    @DeleteMapping("{uuid}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID uuid) {
        orderService.deleteOrder(uuid);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }

    /**
     * Retrieves a paginated list of all orders.
     *
     * @param pageable the pagination and sorting information (page, size, sort)
     * @return HTTP 200 with a page of orders
     *
     *         <b>Integration:</b> Enables efficient data access for UIs and
     *         reporting tools.
     *         <b>Stakeholders:</b> Developers, analysts, integrators.
     */
    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        Page<Order> page = orderService.findAllOrders(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}
