package com.api.products.service;

import com.api.products.entity.Product;
import com.api.products.repository.ProductRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void updateStockProducts(List<Product> products) {
        List<Product> listProductsToUpdate = products.stream().map(produto -> {
            Product prodFound = findById(produto.getId());
            if (prodFound.getQuantity() < produto.getQuantity()) {
                throw new RuntimeException();
            }
            prodFound.setQuantity(prodFound.getQuantity() - produto.getQuantity());
            return prodFound;
        }).collect(Collectors.toList());
        productRepository.saveAll(listProductsToUpdate);
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

}
