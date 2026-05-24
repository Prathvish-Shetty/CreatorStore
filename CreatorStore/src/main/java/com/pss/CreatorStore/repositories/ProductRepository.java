package com.pss.CreatorStore.repositories;

import com.pss.CreatorStore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
