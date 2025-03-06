package com.example.productservice.dtos.product;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;


    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageURL(this.imageUrl);
        Category category = new Category();
        category.setName(categoryName);
        product.setCategory(category);

        return product;
    }
}
