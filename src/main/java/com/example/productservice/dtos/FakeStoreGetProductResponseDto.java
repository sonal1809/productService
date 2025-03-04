package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;


}
