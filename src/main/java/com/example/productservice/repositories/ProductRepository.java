package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductWithTitleAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    // Update is also done using same
    // If a product you try to save has an ID
    // Jpa will see if the product with that id is present
    // if No: it will insert
    // if Yes: it will update
    Product save(Product p);

    void delete(Product entity);

    List<Product> findAll();


    Optional<Product> findById(Long id);

    // select * from products where title = ?
    List<Product> findByTitle(String title);

    // select * from products where title like %title%
    List<Product> findByTitleContains(String title);

    // select * from products where price between ? and ?
    List<Product> findByPriceBetween(Double priceAfter, Double priceBefore);

    List<Product>findByTitleContainsIgnoreCaseAndPriceBetween(String title, Double priceAfter, Double priceBefore);


    // HQL -> Hibernate Query Language
    // Query : Find the Title and price of the product with id = 10
    // Select title , price from products where id = 10;   SQL Query
    @Query("select p.title , p.price from Product p where p.id = ?1")
    List<ProductWithTitleAndPrice> findTitleAndPriceById(Long id);


    // Find the product by Category
    List<Product> findByCategory_Name(String categoryName);

}
