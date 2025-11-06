package com.example.productservice.exceptions;

public class ProductNotFoundException extends Exception{

    private Long productId;


    public ProductNotFoundException(Long productId ,String message){
        super(message);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

}
