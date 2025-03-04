package com.example.productservice.controllers;

import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.dtos.CreateProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Value("${productService}")
    private  String productServiceType;


    // Dependency Injection
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){

        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);

    }

    @GetMapping("")
    public void getAllProducts(){

    }

    @GetMapping("/{id}")
    public CreateProductResponseDto getSingleProduct(@PathVariable("id") long id){
        Product product = productService.getProductById(id);
       return CreateProductResponseDto.fromProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProducts(){}



}
