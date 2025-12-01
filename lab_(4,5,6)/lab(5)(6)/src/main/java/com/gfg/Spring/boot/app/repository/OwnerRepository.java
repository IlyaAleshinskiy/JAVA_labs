package com.gfg.Spring.boot.app.repository;

import com.gfg.Spring.boot.app.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
