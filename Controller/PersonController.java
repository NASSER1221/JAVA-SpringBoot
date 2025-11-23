package com.example.MySQLIntegration.Controller;



import com.example.MySQLIntegration.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MySQLIntegration.Service.Services;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class PersonController {

    @Autowired
private Services services;

    @GetMapping("/getAll")
    public List<Person> getAll(){
        return services.getAllPerson();
    }

    @PostMapping("/add")
    public String addPerson(@RequestBody Person person) throws Exception {
        return services.savePerson(person);
    }

    @GetMapping("/get")
    public Optional<Person> getById(@RequestParam Integer id) throws Exception {
        return services.getById(id);

    }

    @PutMapping("/put")
    public String updatePerson(@RequestBody Person person) throws Exception {
       return services.updatePerson(person);
    }

    @DeleteMapping("/delete")

    public void deletePerson(@RequestParam Integer id) throws Exception {
        services.deletePerson(id);
    }




}
