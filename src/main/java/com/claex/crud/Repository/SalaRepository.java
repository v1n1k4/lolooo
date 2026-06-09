package com.claex.crud.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claex.crud.Entity.SalaEntity;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long>{
    
    Optional<List<SalaEntity>> findByEscolaId(Long id_escola);
}
