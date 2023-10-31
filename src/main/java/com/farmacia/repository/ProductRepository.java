package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.farmacia.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Você não precisa adicionar nenhum método aqui, pois o Spring Data JPA
    // fornece automaticamente os métodos de CRUD (save, findAll, findById, deleteById, etc.).
}
