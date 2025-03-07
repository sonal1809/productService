package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    ProductServiceDBImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Category toBePutInProduct = getCategoryToBeInProduct(product);

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    private Category getCategoryToBeInProduct(Product product) {
        String categoryName = product.getCategory().getName();

        Optional<Category> category = categoryRepository.findByName(categoryName);

        Category toBePutInProduct = null;

        if(category.isEmpty()){
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);
            toBePutInProduct = categoryRepository.save(toSaveCategory);
        }
        else {
            toBePutInProduct = category.get();
        }
        product.setCategory(toBePutInProduct);
        return  toBePutInProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
        Optional<Product> productToUpdateOptional = productRepository.findById(productId);
        if(productToUpdateOptional.isEmpty()){
            throw new RuntimeException();
        }
        Product productToUpdate = productToUpdateOptional.get();
        if(product.getDescription() != null){
            productToUpdate.setDescription(product.getDescription());
        }
        if(product.getPrice() != null){
            productToUpdate.setPrice(product.getPrice());
        }
        if(product.getTitle() != null){
            productToUpdate.setTitle(product.getTitle());
        }
        if(product.getImageURL() != null){
            productToUpdate.setImageURL(product.getImageURL());
        }
        if(product.getCategory() != null){
            Category toBePutInProduct = getCategoryToBeInProduct(product);
            productToUpdate.setCategory(toBePutInProduct);
        }
        return productRepository.save(productToUpdate);
    }
}
