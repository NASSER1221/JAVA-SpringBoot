package com.example.MySQLIntegration.Controller;



import com.example.MySQLIntegration.DTO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MySQLIntegration.Service.Services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class ProductController {

    @Autowired
private Services services;

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return services.getAllProducts();
    }

    @PostMapping("/add")
    public String addPerson(@RequestBody Product product) throws Exception {
        return services.saveProduct(product);
    }

    @GetMapping("/get")
    public Optional<Product> getById(@RequestParam UUID id) throws Exception {
        return services.getById(id);

    }

    @PutMapping("/put")
    public String updateProduct(@RequestBody Product product) throws Exception {
       return services.updateProduct(product);
    }

    @DeleteMapping("/delete")

    public void deleteProduct(@RequestParam UUID id) throws Exception {
        services.deleteProduct(id);
    }




}
