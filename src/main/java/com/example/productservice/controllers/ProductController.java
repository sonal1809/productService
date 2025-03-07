package com.example.productservice.controllers;

import com.example.productservice.dtos.ErrorResponseDto;
import com.example.productservice.dtos.product.*;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    public CreateProductResponseDto getSingleProduct(@PathVariable("id") long id){
        Product product = productService.getProductById(id);
       return CreateProductResponseDto.fromProduct(product);
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

}
