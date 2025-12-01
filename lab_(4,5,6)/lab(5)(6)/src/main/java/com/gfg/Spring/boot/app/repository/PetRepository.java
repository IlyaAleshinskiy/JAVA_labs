package com.gfg.Spring.boot.app.repository;

import com.gfg.Spring.boot.app.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}