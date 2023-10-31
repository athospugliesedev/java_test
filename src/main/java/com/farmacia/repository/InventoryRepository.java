package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.farmacia.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Você não precisa adicionar nenhum método aqui, pois o Spring Data JPA
    // fornece automaticamente os métodos de CRUD (save, findAll, findById, deleteById, etc.).
}
