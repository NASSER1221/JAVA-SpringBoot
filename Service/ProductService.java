package com.example.MySQLIntegration.Service;



import com.example.MySQLIntegration.Entity.Product;
import com.example.MySQLIntegration.Repositery.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(@NotNull UUID id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()&& product.get().getActive()) {
            return product;
        } else {
            throw new Exception("Bad Request");
        }
    }

    public String saveProduct(@NotNull @Valid Product product) throws Exception {
        if (product != null) {
            product.setDate(LocalDate.now());
            product.setActive(Boolean.TRUE);
            product.setUpdatedDate(null);
            productRepository.save(product);
            return "Product Saved";
        } else {
            throw new Exception("Bad Request");
        }
    }

    public String updateProduct( Product product) throws Exception {

        Optional<Product> existingProduct = productRepository.findById(product.getId());


        if (existingProduct.isPresent() && (existingProduct.get().getActive())) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setUpdatedDate(LocalDate.now());
            productRepository.save(updatedProduct);
            return "product updated";

        } else {
            throw new Exception("Bad Request");
        }
    }

    public void deleteProduct(@NotNull String name) throws Exception{

        Optional<Product> existingProduct= productRepository.findByName(name);
        if (existingProduct.isPresent()){
            Product product = existingProduct.get();
            product.setActive(false);
            productRepository.save(product);
            System.out.println("Product Deleted");
        }
        else {
            throw new Exception("Bad Request");
        }
    }


}
