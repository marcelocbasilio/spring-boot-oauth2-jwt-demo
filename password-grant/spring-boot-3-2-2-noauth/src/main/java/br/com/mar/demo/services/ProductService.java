package br.com.mar.demo.services;

import br.com.mar.demo.dto.ProductDTO;
import br.com.mar.demo.entities.Product;
import br.com.mar.demo.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository _productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = _productRepository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return _productRepository.findAll().stream().map(x -> new ProductDTO(x)).toList();
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product = _productRepository.save(product);
        return new ProductDTO(product);
    }
}
