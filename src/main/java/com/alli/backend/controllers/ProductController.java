package com.alli.backend.controllers;

import com.alli.backend.models.Product;
import com.alli.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{type}&{range}")
    public List<Product> getFilterProduct(@PathVariable String type, @PathVariable String range) {
        return productService.getFilterProduct(type, range);
    }

}
