package com.example.basicservice;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
@RestController
@RequestMapping("/fruit")
public class FruitController {
    private ArrayList<String> fruits = new ArrayList<String>(
            Arrays.asList("Mulberry", "Pawpaw")
    );

    // read fruits
    @GetMapping()
    public ResponseEntity<ArrayList<String>> GetPillow() {
        return ResponseEntity.ok(fruits);
    }

    // add fruit by path var
    @PostMapping("{fruit}")
    public ResponseEntity<String> PostPillowPath(@PathVariable String fruit) {
        fruits.add(fruit);
        System.out.println(fruits);
        return ResponseEntity.ok(fruit);
    }

    // read a fruit by index
    @GetMapping("{index}")
    public ResponseEntity<String> getPillowAtIndex(@PathVariable int index) {
        String fruitAtIndex = null;
        try {
            fruitAtIndex = fruits.get(index);
        } catch (Exception ex) {
            return ResponseEntity.status(404).body("not found");
        }
        return ResponseEntity.ok(fruitAtIndex);
    }
    // remove a fruit by index
    @DeleteMapping("{index}")
    public ResponseEntity<String> deletePillowAtIndex(@PathVariable int index) {
        String fruitRemoved = null;
        try {
            fruitRemoved = fruits.remove(index);
            System.out.println(fruitRemoved);
        } catch (Exception ex) {
            return ResponseEntity.status(404).body("not found");
        }
        return ResponseEntity.ok(fruitRemoved + " removed");
    }

    // add fruit by payload
    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> PostPillowBody(@RequestBody String fruit) {
        System.out.println(fruit);
        fruits.add(fruit.replaceAll("\"", ""));
        System.out.println(fruits);
        return ResponseEntity.ok(fruit);
    }

}