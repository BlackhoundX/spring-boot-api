package com.boot.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boot.springbootapi.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
