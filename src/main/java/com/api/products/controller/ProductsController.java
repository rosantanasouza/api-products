package com.api.products.controller;

import com.api.products.entity.Product;
import com.api.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct (@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product),HttpStatus.CREATED );
    }

    @PutMapping("/update-stock")
    public ResponseEntity<String> updateStockProduct(@RequestBody List<Product> products) {
        productService.updateStockProducts(products);
        return new ResponseEntity<>("Estoque de Produtos Atualizados com Sucesso.", HttpStatus.OK);
    }

}
