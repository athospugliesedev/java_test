package com.farmacia.controller;

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
