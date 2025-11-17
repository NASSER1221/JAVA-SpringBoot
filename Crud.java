package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class Crud {

    HashMap<Integer,String> coursesList=new HashMap<>();

    @PostMapping("/add")
    public String add(@RequestParam String name){

        coursesList.put(coursesList.size()+1,name);

        return "Course Added";

    }

    @GetMapping("/getAll")
    public String getAll(){
        return coursesList.toString();
    }

    @GetMapping ("/get/{id}")
    public String courseGetById(@PathVariable Integer id){

        if (coursesList.containsKey(id)){
            System.out.println("Course Found");
            return coursesList.get(id);
        }
        return "Course was not found";

    }
    @PutMapping("/put/{id}")
    public String courseUpdate(@PathVariable Integer id,@RequestParam String name){

        if (coursesList.containsKey(id)){
            coursesList.put(id,name);
            return "Updated";
        }

        return "id was not found";
    }

    @DeleteMapping("/delete/{id}")
    public String courseDelete(@PathVariable Integer id){

        if(coursesList.containsKey(id)){
            coursesList.remove(id);
           return "removed";
        }

        return "id was not found";

    }


}
