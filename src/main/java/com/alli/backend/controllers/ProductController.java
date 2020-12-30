package com.alli.backend.controllers;

import com.alli.backend.models.Product;
import com.alli.backend.models.User;
import com.alli.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // get all products
    // example: {{base_url}}/product/
    //return product List
    @GetMapping("/")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    // get product by type (Health, Retirement), range (High,Mid,Low)
    // example: {{base_url}}/product/Retirement&High
    //return users List
    @GetMapping("/{type}&{range}")
    public List<Product> getFilterProduct(@PathVariable String type, @PathVariable String range) {
        return productService.getFilterProduct(type, range);
    }

    // get product by id
    // example: {{base_url}}/product/240560
    // return 200 and product object if existed
    // return 404 not found if not existed
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        try{
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
