package com.boot.springbootapi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot.springbootapi.Product;
import com.boot.springbootapi.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }

    public ResponseEntity<ArrayList<Product>> fetchAllProducts() {
        ArrayList<Product> list = new ArrayList<>(productRepository.findAll());
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<Optional<Product>> fetchProductById(Long Id) {
        Optional<Product> product = productRepository.findById(Id);
        if(product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        Product savedEntity = productRepository.save(existingProduct);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }
}
