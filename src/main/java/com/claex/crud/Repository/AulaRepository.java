package com.claex.crud.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claex.crud.Entity.AulaEntity;

@Repository
public interface AulaRepository extends JpaRepository<AulaEntity, Long>{
    Optional<List<AulaEntity>> findByGradeId(Long id_grade);
}
