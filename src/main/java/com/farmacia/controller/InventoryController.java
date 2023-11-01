package com.farmacia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.farmacia.model.Inventory;
import com.farmacia.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @PostMapping("/add")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory = inventoryRepository.save(inventory);
        return createdInventory;
    }

    @GetMapping("/all")
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Inventory>> listAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        if (inventoryList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventoryList);
    }

    @PutMapping("/update-quantity/{id}")
    public ResponseEntity<Inventory> updateInventoryQuantity(@PathVariable Long id, @RequestParam int newQuantity) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);

        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setQuantity(newQuantity);
            Inventory updatedInventory = inventoryRepository.save(inventory);
            return ResponseEntity.ok(updatedInventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        if (inventory.isPresent()) {
            return ResponseEntity.ok(inventory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory updatedInventory) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);

        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setProductName(updatedInventory.getProductName());
            inventory.setQuantity(updatedInventory.getQuantity());
            return inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("Inventory not found for id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryRepository.deleteById(id);
    }
}
