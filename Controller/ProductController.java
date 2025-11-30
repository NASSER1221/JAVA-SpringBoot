package com.example.MySQLIntegration.Controller;



import com.example.MySQLIntegration.Entity.Product;
import com.example.MySQLIntegration.Service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class ProductController {

    @Autowired
private ProductService services;

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return services.getAllProducts();
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody @Valid Product product) throws Exception {
        return services.saveProduct(product);
    }

    @GetMapping("/get")
    public Optional<Product> getById(@RequestParam UUID id) throws Exception {
        return services.getById(id);

    }

    @PutMapping("/put")
    public String updateProduct(@RequestBody @Valid Product product) throws Exception {
       return services.updateProduct(product);
    }

    @DeleteMapping("/delete")

    public void deleteProduct(@RequestParam @NotBlank String name) throws Exception {
        services.deleteProduct(name);
    }




}
