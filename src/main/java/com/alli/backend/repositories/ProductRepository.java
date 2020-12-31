package com.alli.backend.repositories;

import com.alli.backend.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{productType : ?0 , productRange : ?1}")
    Stream<Product> findByProductTypeAndProductRange(String productType, String productRange);
}
