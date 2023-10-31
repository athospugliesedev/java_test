package com.farmacia.service;

import com.farmacia.model.Inventory;
import com.farmacia.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);

        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setProductName(updatedInventory.getProductName());
            inventory.setQuantity(updatedInventory.getQuantity());
            // Set other fields as needed
            return inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("Inventory not found for id: " + id);
        }
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
