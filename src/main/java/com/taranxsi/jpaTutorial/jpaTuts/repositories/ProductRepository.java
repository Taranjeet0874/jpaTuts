package com.taranxsi.jpaTutorial.jpaTuts.repositories;

import com.taranxsi.jpaTutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
