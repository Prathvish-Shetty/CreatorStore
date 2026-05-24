package com.pss.CreatorStore.repositories;

import com.pss.CreatorStore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
