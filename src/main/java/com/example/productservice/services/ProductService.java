package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {


    Product createProduct(Product product);// Should take exacts attributes , or models

    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProduct();

    Product partialUpdateProduct(Long id, Product product);

    void search(String keyword, int page, int size, String sortBy);
}
