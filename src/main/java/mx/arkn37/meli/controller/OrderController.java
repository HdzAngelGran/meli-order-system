package mx.arkn37.meli.controller;

import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.model.Order;
import mx.arkn37.meli.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return new ResponseEntity<>("Order updated", HttpStatus.OK);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<Order> getOrderByUuid(@PathVariable UUID uuid) {
        Order order = orderService.orderByUuid(uuid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID uuid) {
        orderService.deleteOrder(uuid);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }

}
