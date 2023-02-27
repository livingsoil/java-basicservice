package com.example.basicservice;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/adventurer")
public class AdventurerController {

    private ArrayList<Adventurer> adventurers = new ArrayList<Adventurer>(
            Arrays.asList(new Adventurer("Trog", AdventurerClasses.FIGHTER, 20, 20 ), new Adventurer("Skiz", AdventurerClasses.MAGE, 10, 50 ))
    );

    // read all adventurers
    @GetMapping()
    public ResponseEntity<String> GetAdventurers() {
        // serialize arraylist
        Gson gson = new Gson();
        String jsonArray = gson.toJson(adventurers);

        return ResponseEntity.ok(jsonArray);
    }

    // read adventurer by index
    @GetMapping(value = "/index/{index}")
    public ResponseEntity<String> getAdventurerAtIndex(@PathVariable("index") int index) {
        Adventurer adventurerAtIndex = null;

        System.out.println(index);
        try {
            adventurerAtIndex = adventurers.get(index);
            System.out.println(adventurerAtIndex);
        } catch (Exception ex) {
            return ResponseEntity.status(404).body("not found");
        }
        Gson gson = new Gson();
        String jsonArray = gson.toJson(adventurerAtIndex);
        return ResponseEntity.ok(jsonArray);
    }

    // read adventurer by name
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<String> getAdventurerByName(@PathVariable String name) {
        Adventurer adventurerByName = null;
        try {
            for(Adventurer adventurer: adventurers) {
                if(adventurer.getName().equals(name)){
                    adventurerByName = adventurer;
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.status(404).body("not found");
        }
        Gson gson = new Gson();
        String res = gson.toJson(adventurerByName);
        return ResponseEntity.ok(res);
    }

    // create adventurer by body payload
    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> PostAdventurerByBody(@RequestBody String adventurer) {
        System.out.println(adventurer); // great success
        Gson gson = new Gson();
        Adventurer newAdventurer = gson.fromJson(adventurer, Adventurer.class);
        System.out.println(adventurer); // great success
        adventurers.add(newAdventurer);
        return ResponseEntity.ok("New adventurer added");
    }

    // update adventurer's health for damage/healing events
    @PutMapping(value = "/health-update/{name}/{increment}")
    public ResponseEntity<String> PutAdventurerHealthUpdate(@PathVariable String name, @PathVariable int increment) {
        System.out.println(name + " " + increment); // great success
        Adventurer adventurer = new Adventurer();
        int initialHealth = 0;
        try {
            for(Adventurer a: adventurers) {
                if(a.getName().equals(name)){
                    adventurer = a;
                    initialHealth = adventurer.getHealth();
                    a.updateHealth(increment);
                }
            }

            if(adventurer.getName() == null){
                throw new NoSuchElementException("Adventurer not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }


        return ResponseEntity.ok(String.format("%1$s's health went from %2$s to %3$s" , adventurer.getName(), initialHealth, adventurer.getHealth()) );
    }
}