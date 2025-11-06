package com.example.productservice.controllers;

import com.example.productservice.dtos.product.*;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Value("${productService}")
    private  String productServiceType;


    // Dependency Injection
    public ProductController(@Qualifier("dbProductService") ProductService productService){
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){

        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);
    }

    @GetMapping("")
    public GetAllProductResponseDto getAllProducts(){
        List<Product> products = productService.getAllProduct();
        GetAllProductResponseDto response = new GetAllProductResponseDto();

        List<GetProductDto> getProductDto = new ArrayList<>();
        for(Product product : products){
            getProductDto.add(GetProductDto.from(product));
        }
        response.setProducts(getProductDto);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProductResponseDto> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return new ResponseEntity<>(
                CreateProductResponseDto.fromProduct(productService.getProductById(id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void deleteProducts(){}

    @PatchMapping("/{id}")
    public PatchProductResponseDto updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequestDto productDto){
        Product product = productService.partialUpdateProduct(id ,productDto.toProduct());
        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));
        return response;
    }

    public void replaceProduct(@PathVariable Long id ,
                               @RequestBody Product product){

    }

}
