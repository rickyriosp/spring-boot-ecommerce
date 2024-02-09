package com.riosr.ecommerce.dao;

import com.riosr.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Query method -> "SELECT * FROM customer WHERE email = ?"
    Customer findByEmail(@Param("email") String email);
}
