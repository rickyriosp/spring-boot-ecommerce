package com.riosr.ecommerce.dao;

import com.riosr.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin    // any origin
@CrossOrigin("http://localhost:4200")
//@CrossOrigin({"http://localhost:4200", "http://mywebsite.com:4200"})   // multiple origins
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Spring Data REST supports pagination out of the box. Just send the parameters for page and size
    // E.g. baseUrl?page=0&size=5

    // Query method -> "SELECT * FROM product WHERE category_id = ?"
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    // Query method -> "SELECT * FROM product WHERE name LIKE CONCAT('%', :name, '%')"
    Page<Product> findByNameContaining(@Param("name") String name, Pageable page);
}
