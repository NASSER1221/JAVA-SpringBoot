package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
@CrossOrigin(origins = "*")
@RestController
public class Crud {

    List<Course> courses = new ArrayList<>();

    @PostMapping("/add")
    public String add(@RequestBody Course course) {

        courses.add(course);
        course.setId(courses.size()+1);
        course.setDate(LocalDate.now());
        course.setActive(true);

        return "Course Added";

    }

    @GetMapping("/getAll")
    public List<Course> getAll() {
        List<Course> activeCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.active) {
                activeCourses.add(course);
            }
        }
        return activeCourses;
    }

    @GetMapping("/get")
    public Object courseGetById(@RequestParam Integer id) {

        for (Course course : courses) {

            if (course.getId().equals(id) && course.active) {
                return course;
            }
        }

        return "Course was not found";

    }

    @PutMapping("/put")
    public Object courseUpdate(@RequestBody Course course) {

        for (Course courseToUpdate : courses) {
            if (course.getId().equals(courseToUpdate.getId())) {
                courseToUpdate.setName(course.getName());
                courseToUpdate.setDate(LocalDate.now());
                courseToUpdate.setEntries(course.getEntries());
                courseToUpdate.setStartingDate(course.getStartingDate());
                courseToUpdate.setEntries(course.getEntries());
                courseToUpdate.setDuration(course.getDuration());
                return "Course updated";
            }
        }

        return "id was not found";
    }

    @DeleteMapping("/delete")
    public String courseDelete(@RequestParam Integer id) {

        for (Course course : courses) {

            if (course.getId().equals(id)) {
                course.setActive(false);
                return "Course deleted";
            }
        }

        return "id was not found";

    }


}
