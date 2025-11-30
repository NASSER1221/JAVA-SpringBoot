package com.example.MySQLIntegration.Service;



import com.example.MySQLIntegration.DTO.Product;
import com.example.MySQLIntegration.Repositery.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Services {

    @Autowired
    private ProductRepo productRepo;


    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getById(UUID id) throws Exception {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent() && product.get().getActive() == Boolean.TRUE) {
            return product;
        } else {
            throw new Exception("Bad Request");
        }
    }

    public String saveProduct(Product product) throws Exception {
        if (product != null) {
            product.setDate(LocalDate.now());
            product.setActive(Boolean.TRUE);
            productRepo.save(product);
            return "Saved";
        } else {
            throw new Exception("Bad Request");
        }
    }

    public String updateProduct(Product product) throws Exception {

        Optional<Product> existingProduct = productRepo.findById(product.getId());


        if (existingProduct.isPresent() && (existingProduct.get().getActive() == Boolean.TRUE)) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDate(LocalDate.now());
            productRepo.save(updatedProduct);
            return "updated";

        } else {
            throw new Exception("Bad Request");
        }
    }

    public void deleteProduct(UUID id) throws Exception{

        Optional<Product> existingProduct= productRepo.findById(id);
        if (existingProduct.isPresent()){
            Product product = existingProduct.get();
            product.setActive(false);
            productRepo.save(product);
            System.out.println("Deleted");
        }
        else {
            throw new Exception("Bad Request");
        }
    }


}
