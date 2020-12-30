package com.alli.backend.services;

import com.alli.backend.models.Product;
import com.alli.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    public List<Product> getFilterProduct(String type, String productRange) {
        List<Product> list = new ArrayList<>();
        try (Stream<Product> stream = productRepo.fineByProductTypeAndProductRange(type, productRange)) {
            stream.forEach(p -> {
                list.add(p);
            });
        }
        return list;
    }

    public Product getProductById(String id){
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
