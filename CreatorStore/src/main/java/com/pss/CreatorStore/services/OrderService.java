package com.pss.CreatorStore.services;

import com.pss.CreatorStore.dto.OrderItemRequest;
import com.pss.CreatorStore.dto.OrderRequest;
import com.pss.CreatorStore.entities.Order;
import com.pss.CreatorStore.entities.OrderItem;
import com.pss.CreatorStore.entities.Product;
import com.pss.CreatorStore.repositories.OrderRepository;
import com.pss.CreatorStore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest){
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest : orderRequest.getItems()){
            Product product = productRepository.findById(
                    itemRequest.getProductId()
            ).orElseThrow(() -> new RuntimeException("Product not found with id " + itemRequest.getProductId()));

//            check product stock
            if(product.getStockQuantity() < itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock for " + itemRequest.getProductId());
            }

//            calculate total price
            BigDecimal priceOfItem = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice = totalPrice.add(priceOfItem);

//            update the product table with latest stock quantity
            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());

            productRepository.save(product);

//            builder pattern to make obj
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();

            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with order id "+id+" not found"));
    }
}
