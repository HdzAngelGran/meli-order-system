package mx.arkn37.meli.service.impl;

import mx.arkn37.meli.dto.CreateOrderRequest;
import mx.arkn37.meli.dto.OrderResponse;
import mx.arkn37.meli.dto.UpdateOrderRequest;
import mx.arkn37.meli.dto.UpdateOrderStatusRequest;
import mx.arkn37.meli.enums.StatusType;
import mx.arkn37.meli.mappers.OrderMapper;
import mx.arkn37.meli.model.Order;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.repository.OrderRepository;
import mx.arkn37.meli.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private StatusService statusService;
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder_shouldSaveOrderWithValidStatus() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setTotal(BigDecimal.TEN);
        request.setAddress("address");
        request.setDescription("desc");
        request.setStatus(StatusType.CREATED);
        Status status = new Status();
        status.setId(1L);
        status.setName("CREATED");
        when(statusService.findByCode(StatusType.CREATED.getCode())).thenReturn(status);

        orderServiceImpl.saveOrder(request);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrder_shouldUpdateOrderFields() {
        UUID uuid = UUID.randomUUID();
        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setAddress("new address");
        request.setDescription("new desc");
        request.setTotal(BigDecimal.ONE);
        Order order = new Order();
        when(orderRepository.findByUuidAndDeletedAtIsNull(uuid)).thenReturn(Optional.of(order));

        orderServiceImpl.updateOrder(uuid, request);
        assertThat(order.getAddress()).isEqualTo("new address");
        assertThat(order.getDescription()).isEqualTo("new desc");
        assertThat(order.getTotal()).isEqualTo(BigDecimal.ONE);
        verify(orderRepository).save(order);
    }

    @Test
    void updateOrderStatus_shouldUpdateStatus() {
        UUID uuid = UUID.randomUUID();
        UpdateOrderStatusRequest request = new UpdateOrderStatusRequest();
        request.setStatus(StatusType.SHIPPED);
        Order order = new Order();
        Status status = new Status();
        status.setId(2L);
        status.setName("SHIPPED");
        when(orderRepository.findByUuidAndDeletedAtIsNull(uuid)).thenReturn(Optional.of(order));
        when(statusService.findByCode(StatusType.SHIPPED.getCode())).thenReturn(status);

        orderServiceImpl.updateOrderStatus(uuid, request);
        assertThat(order.getStatus()).isEqualTo(status);
        verify(orderRepository).save(order);
    }

    @Test
    void orderByUuid_shouldReturnOrderResponse() {
        UUID uuid = UUID.randomUUID();
        Order order = new Order();
        OrderResponse response = new OrderResponse();
        when(orderRepository.findByUuidAndDeletedAtIsNull(uuid)).thenReturn(Optional.of(order));
        try (var mocked = mockStatic(OrderMapper.class)) {
            mocked.when(() -> OrderMapper.toResponse(order)).thenReturn(response);

            OrderResponse result = orderServiceImpl.orderByUuid(uuid);
            assertThat(result).isEqualTo(response);
        }
    }

    @Test
    void findAllOrders_shouldReturnPageOfOrderResponse() {
        Page<Order> orderPage = new PageImpl<>(Collections.singletonList(new Order()));
        when(orderRepository.findAllActive(any())).thenReturn(orderPage);
        try (var mocked = mockStatic(OrderMapper.class)) {
            mocked.when(() -> OrderMapper.toResponse(any(Order.class))).thenReturn(new OrderResponse());

            Page<OrderResponse> result = orderServiceImpl.findAllOrders(PageRequest.of(0, 10));
            assertThat(result.getContent()).hasSize(1);
        }
    }

    @Test
    void deleteOrder_shouldSetDeletedAt() {
        UUID uuid = UUID.randomUUID();
        Order order = new Order();
        when(orderRepository.findByUuid(uuid)).thenReturn(Optional.of(order));

        orderServiceImpl.deleteOrder(uuid);
        assertThat(order.getDeletedAt()).isNotNull();
        verify(orderRepository).save(order);
    }

    @Test
    void updateOrder_shouldThrowIfOrderNotFound() {
        UUID uuid = UUID.randomUUID();
        UpdateOrderRequest request = new UpdateOrderRequest();
        when(orderRepository.findByUuidAndDeletedAtIsNull(uuid)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> orderServiceImpl.updateOrder(uuid, request));
    }

    @Test
    void orderByUuid_shouldThrowIfOrderNotFound() {
        UUID uuid = UUID.randomUUID();
        when(orderRepository.findByUuidAndDeletedAtIsNull(uuid)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> orderServiceImpl.orderByUuid(uuid));
    }
}
