package com.example.productservice.services;

import com.example.productservice.dtos.fakeStore.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.fakeStore.FakeStoreGetProductResponseDto;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        FakeStoreGetProductResponseDto response =  restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );
        return convertToProduct(response);
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreGetProductResponseDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreGetProductResponseDto.class,
                id
        );
        if(response != null) {
            return convertToProduct(response);
        }
        else return null;
    }

    @Override
    public List<Product> getAllProduct() {
        // List<FakeStoreGetProductResponseDto> will not work because at run time java will remove the FakeStoreGetProductResponseDto
        // from the <> to maintain the backward compatibility
        // Only information it will have during run time is they need to convert it to list but the list of what
        // There we will get it array and then will convert to list

        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreGetProductResponseDto[].class
        );

        List<FakeStoreGetProductResponseDto> responseDtoList = Stream.of(response).toList();
        List<Product> products = new ArrayList<>();
        for(FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto : responseDtoList){
            products.add(convertToProduct(fakeStoreGetProductResponseDto));
        }
        return products;
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) {
        FakeStoreGetProductResponseDto productResponseDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/" + id,
                convertToFakeStoreProductRequestDto(product),
                FakeStoreGetProductResponseDto.class
        );
        return convertToProduct(productResponseDto);
    }


    Product convertToProduct(FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto){
        Product product = new Product();
        product.setId(fakeStoreGetProductResponseDto.getId());
        product.setImageURL(fakeStoreGetProductResponseDto.getImage());
        product.setTitle(fakeStoreGetProductResponseDto.getTitle());
        product.setPrice(fakeStoreGetProductResponseDto.getPrice());
        product.setDescription(fakeStoreGetProductResponseDto.getDescription());
        product.setCategory(fakeStoreGetProductResponseDto.getCategory());
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
