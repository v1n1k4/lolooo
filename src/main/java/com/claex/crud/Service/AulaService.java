package com.claex.crud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claex.crud.Entity.AulaEntity;
import com.claex.crud.Repository.AulaRepository;

import java.util.List;

@Service
public class AulaService {
    @Autowired
    private AulaRepository repository;

    public List<AulaEntity> listarTodos() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public AulaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrado"));
    }
    public List<AulaEntity> buscarPorGrade(Long idGrade){
        return repository.findByGradeId(idGrade)
        .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    // SALVAR
    public AulaEntity salvar(AulaEntity aula) {
        return repository.save(aula);
    }

    // ATUALIZAR
    public AulaEntity atualizar(Long id, AulaEntity aula) {

        AulaEntity existente = buscarPorId(id);

        existente.setGrade(aula.getGrade());
        existente.setId_materia(aula.getId_materia());
        existente.setId_professor(aula.getId_professor());
        existente.setDia(aula.getDia());
        existente.setPeriodo(aula.getPeriodo());
    

        return repository.save(existente);
    }

    // DELETAR
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
