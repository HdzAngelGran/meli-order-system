package mx.arkn37.meli.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.dto.CreateOrderRequest;
import mx.arkn37.meli.dto.OrderResponse;
import mx.arkn37.meli.dto.UpdateOrderRequest;
import mx.arkn37.meli.dto.UpdateOrderStatusRequest;
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
@Tag(name = "Order Management", description = "APIs for creating, reading, updating, and deleting orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order based on the provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<String> saveOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.saveOrder(createOrderRequest);
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }

    @PutMapping("{uuid}")
    @Operation(summary = "Update an existing order", description = "Updates the details of an existing order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<String> updateOrder(@PathVariable UUID uuid, @RequestBody UpdateOrderRequest updateOrderRequest) {
    orderService.updateOrder(uuid, updateOrderRequest);
        return new ResponseEntity<>("Order updated", HttpStatus.OK);
    }

    @GetMapping("{uuid}")
    @Operation(summary = "Get an order by UUID", description = "Retrieves a single order by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResponse> getOrderByUuid(@PathVariable UUID uuid) {
        OrderResponse order = orderService.orderByUuid(uuid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    @Operation(summary = "Delete an order by UUID", description = "Soft-deletes an order by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<String> deleteOrder(@PathVariable UUID uuid) {
        orderService.deleteOrder(uuid);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves a paginated list of all orders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {
        Page<OrderResponse> page = orderService.findAllOrders(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PutMapping("{uuid}/status")
    @Operation(summary = "Update the status of an order", description = "Updates the status of an existing order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<String> updateOrderStatus(@PathVariable UUID uuid, @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderService.updateOrderStatus(uuid, updateOrderStatusRequest);
        return new ResponseEntity<>("Order status updated", HttpStatus.OK);
    }

}
