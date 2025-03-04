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
        FakeStoreCreateProductRequestDto request = convertToFakeStoreProductRequestDto(product);
        FakeStoreCreateProductResponseDto response =  restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreCreateProductResponseDto.class
        );
        return convertToProduct(response);
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreCreateProductResponseDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreCreateProductResponseDto.class,
                id
        );
        if(response != null) {
            return convertToProduct(response);
        }
        else return null;
    }



    Product convertToProduct(FakeStoreCreateProductResponseDto fakeStoreCreateProductResponseDto){
        Product product = new Product();
        product.setId(fakeStoreCreateProductResponseDto.getId());
        product.setImageURL(fakeStoreCreateProductResponseDto.getImage());
        product.setTitle(fakeStoreCreateProductResponseDto.getTitle());
        product.setPrice(fakeStoreCreateProductResponseDto.getPrice());
        product.setDescription(fakeStoreCreateProductResponseDto.getDescription());
        product.setCategory(fakeStoreCreateProductResponseDto.getCategory());
        return product;
    }

    FakeStoreCreateProductRequestDto convertToFakeStoreProductRequestDto(Product product){
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getCategory());
        request.setDescription(product.getDescription());
        request.setTitle(product.getTitle());
        request.setPrice(product.getPrice());
        request.setCategory(product.getCategory());
        request.setImage(product.getImageURL());
        return  request;
    }
}
