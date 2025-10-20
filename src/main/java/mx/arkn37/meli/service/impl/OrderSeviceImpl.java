package mx.arkn37.meli.service.impl;

/**
 * Implementation of OrderService for business logic and integration.
 * <p>
 * Handles persistence, validation, and extensibility for order operations.
 * <ul>
 *   <li>Integrates with repositories and digital tools.</li>
 *   <li>Adaptable for new business requirements and workflows.</li>
 * </ul>
 *
 * @author Angel Hernandez
 */

import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.model.Order;
import mx.arkn37.meli.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.arkn37.meli.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderSeviceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UUID currentUser = UUID.fromString("ed943e94-4b8c-40c8-be20-0da27027fb89");

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrder(Order order) {
        order.setCreatedBy(currentUser);
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrder(Order order) {
        Order originaOrder = orderRepository.findByUuidAndDeleteAtIsNull(order.getUuid())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with UUID: " + order.getUuid()));

        if (order.getAddress() != null) {
            originaOrder.setAddress(order.getAddress());
        }
        if (order.getDescription() != null) {
            originaOrder.setDescription(order.getDescription());
        }
        if (order.getTotal() != null) {
            originaOrder.setTotal(order.getTotal());
        }

        originaOrder.setUpdatedBy(currentUser);
        orderRepository.save(originaOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order orderByUuid(UUID uuid) {
        return orderRepository.findByUuidAndDeleteAtIsNull(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with UUID: " + uuid));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderRepository.findAllActive(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOrder(UUID uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with UUID: " + uuid));

        if (order.getDeleteAt() != null) {
            throw new IllegalStateException("Order with UUID " + uuid + " is already deleted.");
        }

        order.setDeleteAt(LocalDateTime.now());
        order.setUpdatedBy(currentUser);

        orderRepository.save(order);
    }
}
