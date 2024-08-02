package com.example.catalog.controller;


import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import com.example.catalog.service.ProductService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductController {

ProductService productService;
    ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductController.class);
@PostMapping("/add")
//Add the product
public ResponseEntity<String> addProduct(@RequestBody Product product) {
    logger.info("addProduct API has started");
    try {
        // Call the service to add the product and get the response
        String response = productService.addProduct(product);
        logger.info("addProduct API has ended");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
        // Prepare an error message with the exception details
        String msg = "Product cannot be added: " + e.getMessage();
        logger.error("addProduct API failed due to error - " + e.getMessage(), e);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


    @GetMapping("/getProductByName")
    public ResponseEntity<?> getProductByName(@RequestParam String name) {
        logger.info("Starting getProductByName API");
        try {
            Product product = productService.getProductByName(name);
            logger.info("getProductByName API successful");
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.error("Error in getProductByName API: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product not found");
        }
    }

@GetMapping("/getProductByBrand")
public ResponseEntity<?> getProductsByBrand(@RequestParam String brand) {
    logger.info("Starting getProductByBrand API");
    try {
        List<Product> products = productService.getProductByBrand(brand);
        logger.info("getProductByBrand API successful");
        return ResponseEntity.ok(products);
    } catch (Exception e) {
        logger.error("Error in getProductByBrand API: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Product's brand is not available");
    }
}

@GetMapping("/getProductByCategory")
public ResponseEntity<?> getProductByCategory(@RequestParam Category category) {
    // Log the start of the API call
    logger.info("Starting getProductByCategory API");
    try {
        // Retrieve products by category
        List<Product> products = productService.getProductByCategory(category);
        logger.info("getProductByCategory API successful");
        return ResponseEntity.ok(products);
    } catch (Exception e) {
        logger.error("Error in getProductByCategory API: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Category does not exist");
    }
}

@PutMapping("/updateById")
public ResponseEntity<?> updateProductById(@RequestParam Integer productId,
                                           @RequestBody Product product) {
    logger.info("Starting update API");
    try {
        productService.updateProductById(productId, product);
        Product updatedProduct = productRepository.findById(productId).orElse(null);
        logger.info("Update API successful");
        return ResponseEntity.ok(updatedProduct);
    } catch (Exception e) {
        logger.error("Update API failed: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Invalid entry");
    }
}


   @PutMapping("/updateByName")
public ResponseEntity<String> updateProductByName(@RequestParam String name, @RequestBody Product product) {
    logger.info("Starting updateProductByName API");
    try {
        String result = productService.updateProductByName(name, product);
        logger.info("updateProductByName API successful");
        return ResponseEntity.ok(result);
    } catch (Exception e) {
        logger.error("Update failed: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Can't update the product");
    }
}

@DeleteMapping("/delete")
public ResponseEntity<String> deleteProduct(@RequestParam Integer productId) {
    logger.info("Starting delete API");
    if (!productRepository.existsById(productId)) {
        logger.error("Delete failed: Id is invalid");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Id is invalid");
    }
    productService.deleteProduct(productId);
    logger.info("Product deleted successfully");
    return ResponseEntity.ok("Product successfully deleted");
}
}