package mx.arkn37.meli.service;

import mx.arkn37.meli.model.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface OrderService {

    void saveOrder(Order order);

    void updateOrder(Order order);

    Order orderByUuid(UUID uuid);

    void deleteOrder(UUID uuid);

}