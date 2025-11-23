package com.example.MySQLIntegration.Service;


import com.example.MySQLIntegration.Person;
import com.example.MySQLIntegration.Repositery.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Services {

    @Autowired
    private PersonRepo personRepo;


    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    public Optional<Person> getById(Integer id) throws Exception {
        Optional<Person> person = personRepo.findById(id);
        if (person.isPresent() && person.get().getActive() == Boolean.TRUE) {
            return person;
        } else {
            throw new Exception("Bad Request");
        }
    }

    public String savePerson(Person person) throws Exception {
        if (person != null) {
            person.setDate(LocalDate.now());
            person.setActive(Boolean.TRUE);
            personRepo.save(person);
            return "Saved";
        } else {
            throw new Exception("Bad Request");
        }
    }

    public String updatePerson(Person person) throws Exception {

        Optional<Person> existingPerson = personRepo.findById(person.getId());


        if (existingPerson.isPresent() && (existingPerson.get().getActive() == Boolean.TRUE)) {
            Person updatedPerson = existingPerson.get();
            updatedPerson.setName(person.getName());
            updatedPerson.setDate(LocalDate.now());
            personRepo.save(updatedPerson);
            return "updated";

        } else {
            throw new Exception("Bad Request");
        }
    }

    public void deletePerson(Integer id) throws Exception{

        Optional<Person> existingPerson= personRepo.findById(id);
        if (existingPerson.isPresent()){
            Person person = existingPerson.get();
            person.setActive(false);     
            personRepo.save(person);
            System.out.println("Deleted");
        }
        else {
            throw new Exception("Bad Request");
        }
    }


}
