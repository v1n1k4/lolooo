package com.claex.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claex.crud.Entity.GradeEntity;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long>{

}
