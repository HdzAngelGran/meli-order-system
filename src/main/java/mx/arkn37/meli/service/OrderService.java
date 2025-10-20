package mx.arkn37.meli.service;

import mx.arkn37.meli.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    void saveOrder(Order order);

    void updateOrder(Order order);

    Order orderByUuid(UUID uuid);

    void deleteOrder(UUID uuid);

    Page<Order> findAllOrders(Pageable pageable);

}