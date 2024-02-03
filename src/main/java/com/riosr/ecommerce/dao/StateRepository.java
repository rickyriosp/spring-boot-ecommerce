package com.riosr.ecommerce.dao;

import com.riosr.ecommerce.entity.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface StateRepository extends JpaRepository<State, Long> {

    // Query method -> "SELECT * FROM state WHERE country_id = (SELECT country_id FROM country WHERE code = ?)"
    // Eg: /api/states/search/findByCountryCode?code=IN
    List<State> findByCountryCode(@Param("code") String code);
}
