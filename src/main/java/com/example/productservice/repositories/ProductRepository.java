package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    // Update is also done using same
    // If a product you try to save has an ID
    // Jpa will see if the prtoduct with that id is present
    // if No: it will insert
    // if Yes: it will update
    @Override
    Product save(Product p);

    @Override
    void delete(Product entity);
}
