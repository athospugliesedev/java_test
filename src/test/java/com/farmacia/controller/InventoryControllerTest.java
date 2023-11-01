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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    public void testUpdateInventory_NotFound() {
        Long id = 1L;
        Inventory updatedInventory = new Inventory();

        Mockito.when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> inventoryController.updateInventory(id, updatedInventory));
    }

    @Test
    public void testDeleteInventory() {
        Long id = 1L;

        assertDoesNotThrow(() -> inventoryController.deleteInventory(id));
    }

    // novos testes

    @Test
public void testDeleteInventory_Existing() {
    Long id = 1L;
    
    assertDoesNotThrow(() -> inventoryController.deleteInventory(id));
}

@Test
public void testGetAllInventoryEmpty() {
    Mockito.when(inventoryRepository.findAll()).thenReturn(Collections.emptyList());

    List<Inventory> result = inventoryController.getAllInventory();

    assertNotNull(result);
    assertTrue(result.isEmpty());
}
@Test
public void testListAllInventory() {
    List<Inventory> inventoryList = Collections.emptyList();

    Mockito.when(inventoryRepository.findAll()).thenReturn(inventoryList);

    ResponseEntity<List<Inventory>> response = inventoryController.listAllInventory();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
}

}
