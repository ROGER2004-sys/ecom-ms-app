package org.example.inventoryservice;

import org.example.inventoryservice.Entity.Product;
import org.example.inventoryservice.Repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner loadProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                        new Product("Ordinateur portable", new BigDecimal("899.99"), 10),
                        new Product("Clavier sans fil", new BigDecimal("49.90"), 25),
                        new Product("Souris optique", new BigDecimal("19.99"), 40)
                ));
            }
        };
    }
}
