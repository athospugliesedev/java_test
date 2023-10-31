package com.farmacia.controller;

import com.farmacia.model.Inventory;
import com.farmacia.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class InventoryControllerTest {

    @InjectMocks
    private InventoryController inventoryController;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductName("Test Product");
        inventory.setQuantity(10);

        Mockito.when(inventoryRepository.save(Mockito.any(Inventory.class))).thenReturn(inventory);

        Inventory createdInventory = inventoryController.createInventory(inventory);

        assertEquals("Test Product", createdInventory.getProductName());
        assertEquals(10, createdInventory.getQuantity());
    }

    @Test
    public void testGetAllInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(new Inventory());
        inventoryList.add(new Inventory());

        Mockito.when(inventoryRepository.findAll()).thenReturn(inventoryList);

        List<Inventory> result = inventoryController.getAllInventory();

        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateInventory() {
        Long id = 1L;
        Inventory existingInventory = new Inventory();
        existingInventory.setId(id);
        existingInventory.setProductName("Existing Product");
        existingInventory.setQuantity(5);

        Inventory updatedInventory = new Inventory();
        updatedInventory.setId(id);
        updatedInventory.setProductName("Updated Product");
        updatedInventory.setQuantity(20);

        Mockito.when(inventoryRepository.findById(id)).thenReturn(Optional.of(existingInventory));
        Mockito.when(inventoryRepository.save(Mockito.any(Inventory.class))).thenReturn(updatedInventory);

        Inventory result = inventoryController.updateInventory(id, updatedInventory);

        assertEquals("Updated Product", result.getProductName());
        assertEquals(20, result.getQuantity());
    }

    @Test
    public void testUpdateInventory_NotFound() {
        Long id = 1L;
        Inventory updatedInventory = new Inventory();

        Mockito.when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> inventoryController.updateInventory(id, updatedInventory));
    }

    @Test
    public void testDeleteInventory() {
        Long id = 1L;

        inventoryController.deleteInventory(id);

        Mockito.verify(inventoryRepository, Mockito.times(1)).deleteById(id);
    }
}
