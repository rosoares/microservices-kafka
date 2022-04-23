package br.rodrigo.orders.controllers;

import br.rodrigo.orders.models.Order;
import br.rodrigo.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> postOrder(@RequestBody Order order) {

        orderService.sendMessage(order);

        return ResponseEntity.ok("Sended");
    }

}
