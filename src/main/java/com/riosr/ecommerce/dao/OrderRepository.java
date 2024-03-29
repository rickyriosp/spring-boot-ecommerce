package com.riosr.ecommerce.dao;

import com.riosr.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Query method
    // SELECT * FROM orders LEFT OUTER JOIN customer ON orders.customer_id = customer.id WHERE customer.email = :email
    @PreAuthorize("principal.subject == #email")
    Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);
}
