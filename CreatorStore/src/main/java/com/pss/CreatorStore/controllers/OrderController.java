package com.pss.CreatorStore.controllers;

import com.pss.CreatorStore.dto.OrderRequest;
import com.pss.CreatorStore.entities.Order;
import com.pss.CreatorStore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

//    get all orders
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

//    get order by id
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }


}
