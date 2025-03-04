package com.example.productservice.dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;

    public static GetProductDto from(Product product) {
        GetProductDto responseDto = new GetProductDto();
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageURL(product.getImageURL());
        responseDto.setCategory(product.getCategory());
        responseDto.setId(product.getId());
        return responseDto;
    }
}
