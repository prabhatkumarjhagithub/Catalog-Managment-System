package com.example.catalog.repository;
import com.example.catalog.model.Product;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    // Find a product by its name
    public Product findByName(String name);

    // Find a list of products by their brand
    public List<Product> findByBrand(String brand);

    // Find a list of products by their category
    public List<Product> findByCategory(Category category);
}