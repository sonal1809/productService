package com.example.productservice.dtos.fakeStore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {

    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
