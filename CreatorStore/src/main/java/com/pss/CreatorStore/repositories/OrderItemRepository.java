package com.pss.CreatorStore.repositories;

import com.pss.CreatorStore.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
