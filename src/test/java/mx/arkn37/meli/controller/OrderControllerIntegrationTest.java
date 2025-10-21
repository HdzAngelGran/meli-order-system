package mx.arkn37.meli.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.arkn37.meli.dto.CreateOrderRequest;
import mx.arkn37.meli.dto.OrderResponse;
import mx.arkn37.meli.dto.UpdateOrderRequest;
import mx.arkn37.meli.dto.UpdateOrderStatusRequest;
import mx.arkn37.meli.enums.StatusType;
import mx.arkn37.meli.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private OrderService orderService;

  @Test
  void saveOrder_shouldReturnCreated() throws Exception {
    CreateOrderRequest request = new CreateOrderRequest();
    request.setTotal(BigDecimal.TEN);
    request.setAddress("address");
    request.setDescription("desc");
    request.setStatus(StatusType.CREATED);

    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(content().string("Order created"));
  }

  @Test
  void updateOrder_shouldReturnOk() throws Exception {
    UUID uuid = UUID.randomUUID();
    UpdateOrderRequest request = new UpdateOrderRequest();
    request.setAddress("new address");
    request.setDescription("new desc");
    request.setTotal(BigDecimal.ONE);

    mockMvc.perform(put("/order/" + uuid)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(content().string("Order updated"));
  }

  @Test
  void getOrderByUuid_shouldReturnOrder() throws Exception {
    UUID uuid = UUID.randomUUID();
    OrderResponse response = new OrderResponse();
    Mockito.when(orderService.orderByUuid(uuid)).thenReturn(response);

    mockMvc.perform(get("/order/" + uuid))
        .andExpect(status().isOk());
  }

  @Test
  void deleteOrder_shouldReturnOk() throws Exception {
    UUID uuid = UUID.randomUUID();
    mockMvc.perform(delete("/order/" + uuid))
        .andExpect(status().isOk())
        .andExpect(content().string("Order deleted"));
  }

  @Test
  void getAllOrders_shouldReturnPage() throws Exception {
    Page<OrderResponse> page = new PageImpl<>(Collections.singletonList(new OrderResponse()));
    Mockito.when(orderService.findAllOrders(any(PageRequest.class))).thenReturn(page);

    mockMvc.perform(get("/order?page=0&size=10"))
        .andExpect(status().isOk());
  }

  @Test
  void updateOrderStatus_shouldReturnOk() throws Exception {
    UUID uuid = UUID.randomUUID();
    UpdateOrderStatusRequest request = new UpdateOrderStatusRequest();
    request.setStatus(StatusType.SHIPPED);

    mockMvc.perform(put("/order/" + uuid + "/status")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(content().string("Order status updated"));
  }
}
