package com.example.productservice.dtos.product;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageURL(product.getImageURL());
        responseDto.setCategory(product.getCategory());
        responseDto.setId(product.getId());
        return responseDto;
    }
}
