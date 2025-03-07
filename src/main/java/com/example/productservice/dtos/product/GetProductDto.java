package com.example.productservice.dtos.product;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String imageURL;

    public static GetProductDto from(Product product) {
        GetProductDto responseDto = new GetProductDto();
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageURL(product.getImageURL());
        responseDto.setId(product.getId());
        return responseDto;
    }
}
