package com.sujit.ordersystemspringweb.controller;

import com.sujit.ordersystemspringweb.repository.Item;
import com.sujit.ordersystemspringweb.repository.ItemRepository;
import com.sujit.ordersystemspringweb.model.Error;
import com.sujit.ordersystemspringweb.validation.ItemValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository repository;
    private final ItemValidator itemValidator;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Item newItem) {
        List<Error> errors = itemValidator.validate(newItem);
        if (errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(repository.save(newItem));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return ResponseEntity.ok((List<Item>) repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Item updateItem, @PathVariable Long id) {
        List<Error> errors = itemValidator.validate(updateItem);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        Optional<Item> exists = repository.findById(id);
        if (exists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Item item = exists.get();
        item.setItemName(updateItem.getItemName());
        item.setBrand(updateItem.getBrand());
        item.setStore(updateItem.getStore());
        item.setPrice(updateItem.getPrice());
        item.setDescription(updateItem.getDescription());
        item.setDiscount(updateItem.getDiscount());
        return ResponseEntity.ok(repository.save(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWithId(@PathVariable Long id) {
        Optional<Item> exists = repository.findById(id);
        if (exists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{brand}")
    public ResponseEntity<List<Item>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(repository.findByBrand(brand));
    }

}
