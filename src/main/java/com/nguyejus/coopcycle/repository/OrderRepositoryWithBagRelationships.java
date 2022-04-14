package com.nguyejus.coopcycle.repository;

import com.nguyejus.coopcycle.domain.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OrderRepositoryWithBagRelationships {
    Optional<Order> fetchBagRelationships(Optional<Order> order);

    List<Order> fetchBagRelationships(List<Order> orders);

    Page<Order> fetchBagRelationships(Page<Order> orders);
}
