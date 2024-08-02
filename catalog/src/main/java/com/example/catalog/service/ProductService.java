package com.example.catalog.service;
import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);
    ProductRepository productRepository;

public String addProduct(Product product) {
    logger.info("Adding product");
    product.setCreatedOn(new Date());
    product.setUpdatedOn(new Date());
    productRepository.save(product);
    logger.info("Product added successfully");
    return "Product added successfully";
}


    public List<Product> getProductByBrand(String brand){
        logger.info("GetProductByBrand method has been called");
        List<Product> product = productRepository.findByBrand(brand);
        logger.info("GetProductByBrand method has been ended");
        return product;
    }
        public Product getProductByName(String name) {
         logger.info("Fetching product by name");
         return productRepository.findByName(name);
         }


    public List<Product> getProductByCategory(Category category){
        logger.info("GetProductByCategory method has been called");
        List<Product> product = productRepository.findByCategory(category);
        logger.info("GetProductByCategory method has been ended");
        return product;
    }

    public void updateProductById(int productId, Product product) throws Exception{
        logger.info("UpdateProductById method has been called");
        Product getProduct = productRepository.findById(productId).orElse(null);
        getProduct.setBrand(product.getBrand());
        getProduct.setName(product.getName());
        getProduct.setCategory(product.getCategory());
        getProduct.setPrice(product.getPrice());
        getProduct.setQuantity(product.getQuantity());
        getProduct.setDescription(product.getDescription());
        getProduct.setCreatedOn(getProduct.getCreatedOn());
        getProduct.setUpdatedOn(new Date());
        productRepository.save(getProduct);
        logger.info("UpdateProductById method has been ended");
    }

    public String updateProductByName(String name, Product product) throws Exception{
        Product getProduct = productRepository.findByName(name);

        if(product == null){
            throw new RuntimeException(String.format("Not Available ",name));
        }
        logger.info("UpdateProductByName method has been called");
        getProduct.setBrand(product.getBrand());
        getProduct.setName(product.getName());
        getProduct.setCategory(product.getCategory());
        getProduct.setPrice(product.getPrice());
        getProduct.setQuantity(product.getQuantity());
        getProduct.setDescription(product.getDescription());
        getProduct.setCreatedOn(getProduct.getCreatedOn());
        getProduct.setUpdatedOn(new Date());
        productRepository.save(getProduct);
        logger.info("UpdateProductByName method has been ended");
        return "Details updated successfully";

    }

    public void deleteProduct(Integer productId){
        logger.info("DeleteProduct method has been called");
        productRepository.deleteById(productId);
        logger.info("DeleteProduct method has been ended");
    }

}