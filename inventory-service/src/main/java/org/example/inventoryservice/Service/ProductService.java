package org.example.inventoryservice.Service;

import org.example.inventoryservice.DTO.ProductRequestDTO;
import org.example.inventoryservice.DTO.ProductResponseDTO;
import org.example.inventoryservice.Entity.Product;
import org.example.inventoryservice.Mapper.ProductMapper;
import org.example.inventoryservice.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product non trouvé avec l'id : " + id));
        return productMapper.toDTO(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = productMapper.toEntity(requestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product non trouvé avec l'id : " + id);
        }
        productRepository.deleteById(id);
    }
}
