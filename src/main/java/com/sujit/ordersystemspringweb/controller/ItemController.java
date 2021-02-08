package com.sujit.ordersystemspringweb.controller;

import com.sujit.ordersystemspringweb.repository.Item;
import com.sujit.ordersystemspringweb.repository.ItemRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item newItem) {

        return ResponseEntity.ok(repository.save(newItem));
    }
    @GetMapping
    public ResponseEntity<List<Item>> getAll(){

        return ResponseEntity.ok((List<Item>) repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@RequestBody Item updateItem, @PathVariable Long id) {
       return repository.findById(id)
               .map(item -> {
                   item.setItemName(updateItem.getItemName());
                   item.setBrand(updateItem.getBrand());
                   item.setStore(updateItem.getStore());
                   item.setPrice(updateItem.getPrice());
                   item.setDescription(updateItem.getDescription());
                   item.setDiscount(updateItem.getDiscount());
                   return ResponseEntity.ok(repository.save(item));
               })
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity deleteItem(@RequestBody Item item) {
        if(repository.existsById(item.getId())){
            repository.delete(item);
            return (ResponseEntity) ResponseEntity.ok();
        }
        else return (ResponseEntity) ResponseEntity.notFound();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWithId(@PathVariable Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return (ResponseEntity) ResponseEntity.ok();
        }
        else return (ResponseEntity) ResponseEntity.notFound();

    }
    @GetMapping("{brand}")
    public ResponseEntity<List<Item>> getByBrand( @PathVariable String brand){
    return ResponseEntity.ok(repository.findByBrand(brand));
    }




}
