package mx.arkn37.meli.mappers;

import mx.arkn37.meli.dto.OrderResponse;
import mx.arkn37.meli.model.Order;

public class OrderMapper {

    private OrderMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderResponse toResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setAddress(order.getAddress());
        orderResponse.setDescription(order.getDescription());
        orderResponse.setTotal(order.getTotal());
        orderResponse.setStatus(order.getStatus().getCode());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setDeletedAt(order.getDeletedAt());
        return orderResponse;
    }
}
