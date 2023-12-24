package com.riosr.ecommerce.dao;

import com.riosr.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin    // any origin
@CrossOrigin("http://localhost:4200")
//@CrossOrigin({"http://localhost:4200", "http://mywebsite.com:4200"})   // multiple origins
public interface ProductRepository extends JpaRepository<Product, Long> {
}
