Check your maven version
```bash
mvn -v
```
Check your java version
```bash
java --version 
```
To run the project
```bash
mvn spring-boot:run
```
Url to access application: http://localhost:8080

### Fork this repository to your own Github and create a branch for each exercise

## Exercises:

### Fruits Api
**Goal**: Create an API that will allow basic crud operations to a list of fruits
* Create a FruitController in package com.example.basicservice.controllers
* Map to the url */fruit*
* Create an ArrayList to hold the fruits
* Create a handler for a **GET** request that will return the array of fruits
* Create a handler for a **POST** request that will take the name of a fruit as a parameter and add to the array
* Create a handler for a **GET** request that will take an index for a fruit in the array and return it
* Create a handler for a **DELETE** request that will take the index of a fruit in the array and remove it

**Bonus** Create a **POST** request that accepts the name of a fruit in the request body instead of the url

<details>
<summary>HINTS</summary>

```java
// Annotations
@RestController
@RequestMapping
@GetMapping
@PostMapping
@DeleteMapping
@PathVariable
```
</details>