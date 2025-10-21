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
import mx.arkn37.meli.dto.CreateOrderRequest;
import mx.arkn37.meli.dto.OrderResponse;
import mx.arkn37.meli.dto.UpdateOrderRequest;
import mx.arkn37.meli.dto.UpdateOrderStatusRequest;
import mx.arkn37.meli.mappers.OrderMapper;
import mx.arkn37.meli.model.Order;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.arkn37.meli.service.OrderService;
import mx.arkn37.meli.service.StatusService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderSeviceImpl implements OrderService {

    private static final String ORDER_NOT_FOUD = "Order not found with UUID: ";

    private final OrderRepository orderRepository;
    private final StatusService statusService;
    private final UUID currentUser = UUID.fromString("ed943e94-4b8c-40c8-be20-0da27027fb89");

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrder(CreateOrderRequest createOrderRequest) {

        Status status = statusService.findByCode(createOrderRequest.getStatus().getCode());

        Order order = new Order();
        order.setTotal(createOrderRequest.getTotal());
        order.setStatus(status);
        order.setAddress(createOrderRequest.getAddress());
        order.setDescription(createOrderRequest.getDescription());
        order.setCreatedBy(currentUser);
        order.setCreatedAt(LocalDateTime.now());

        orderRepository.save(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrder(UUID orderUuid, UpdateOrderRequest updateOrderRequest) {
        Order originaOrder = orderRepository.findByUuidAndDeletedAtIsNull(orderUuid)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUD + orderUuid));

        if (updateOrderRequest.getAddress() != null) {
            originaOrder.setAddress(updateOrderRequest.getAddress());
        }
        if (updateOrderRequest.getDescription() != null) {
            originaOrder.setDescription(updateOrderRequest.getDescription());
        }
        if (updateOrderRequest.getTotal() != null) {
            originaOrder.setTotal(updateOrderRequest.getTotal());
        }

        originaOrder.setUpdatedBy(currentUser);
        originaOrder.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(originaOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrderStatus(UUID orderUuid, UpdateOrderStatusRequest updateOrderStatusRequest) {
        Order originaOrder = orderRepository.findByUuidAndDeletedAtIsNull(orderUuid)
            .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUD + orderUuid));

        if (updateOrderStatusRequest.getStatus() == null) throw new IllegalArgumentException();
        Status status = statusService.findByCode(updateOrderStatusRequest.getStatus().getCode());
        originaOrder.setStatus(status);
        orderRepository.save(originaOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderResponse orderByUuid(UUID uuid) {
        Order order = orderRepository.findByUuidAndDeletedAtIsNull(uuid)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUD + uuid));
        return OrderMapper.toResponse(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<OrderResponse> findAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAllActive(pageable);
        return orders.map(OrderMapper::toResponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOrder(UUID uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUD + uuid));

        if (order.getDeletedAt() != null) {
            throw new IllegalStateException("Order with UUID " + uuid + " is already deleted.");
        }

        order.setDeletedAt(LocalDateTime.now());
        order.setUpdatedBy(currentUser);

        orderRepository.save(order);
    }
}
