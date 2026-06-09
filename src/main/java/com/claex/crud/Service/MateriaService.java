package com.claex.crud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claex.crud.Entity.MateriaEntity;
import com.claex.crud.Repository.MateriaRepository;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository repository;
    
    public List<MateriaEntity> listarTodos() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public MateriaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia não encontrado"));
    }

    // SALVAR
    public MateriaEntity salvar(MateriaEntity materia) {
        return repository.save(materia);
    }

    // ATUALIZAR
    public MateriaEntity atualizar(Long id, MateriaEntity materia) {

        MateriaEntity existente = buscarPorId(id);

        existente.setNome_materia(materia.getNome_materia());

        return repository.save(existente);
    }

    // DELETAR
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
