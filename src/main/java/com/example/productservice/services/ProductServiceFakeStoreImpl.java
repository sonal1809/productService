package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.FakeStoreCreateProductResponseDto;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class ProductServiceFakeStoreImpl implements ProductService{

    // For Third Party
    private RestTemplate restTemplate;

    // Object of RestTemplate is not autowired , because it is not present as a spring bean
    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getCategory());
        request.setDescription(product.getDescription());
        request.setTitle(product.getTitle());
        request.setPrice(product.getPrice());
        request.setCategory(product.getCategory());
        request.setImage(product.getImageURL());


        FakeStoreCreateProductResponseDto response =  restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreCreateProductResponseDto.class
        );

        Product product1 = new Product();
        product1.setId(response.getId());
        product1.setImageURL(response.getImage());
        product1.setTitle(response.getTitle());
        product1.setPrice(response.getPrice());
        product1.setDescription(response.getDescription());
        product1.setCategory(response.getCategory());

        return product1;
    }
}
