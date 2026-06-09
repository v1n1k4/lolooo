package com.claex.crud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claex.crud.Entity.ProfessorEntity;
import com.claex.crud.Repository.ProfessorRepository;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;
    
    public List<ProfessorEntity> listarTodos() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public ProfessorEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    // SALVAR
    public ProfessorEntity salvar(ProfessorEntity professor) {
        return repository.save(professor);
    }

    // ATUALIZAR
    public ProfessorEntity atualizar(Long id, ProfessorEntity professor) {

        ProfessorEntity existente = buscarPorId(id);

        existente.setNome_professor(professor.getNome_professor());
        existente.setEmail_professor(professor.getEmail_professor());
        existente.setSenha_professor(professor.getSenha_professor());

        return repository.save(existente);
    }

    // DELETAR
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
