package com.claex.crud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claex.crud.Entity.SalaEntity;
import com.claex.crud.Repository.SalaRepository;
import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    // LISTAR TODOS
    public List<SalaEntity> listarTodos() {
        return repository.findAll();
    }

    public List<SalaEntity> BuscarPorEscola(Long id_escola){
        return repository.findByEscolaId(id_escola).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
    }
    // BUSCAR POR ID
    public SalaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
    }

    // SALVAR
    public SalaEntity salvar(SalaEntity sala) {
        return repository.save(sala);
    }

    // ATUALIZAR
    public SalaEntity atualizar(Long id, SalaEntity sala) {

        SalaEntity existente = buscarPorId(id);

        existente.setNome_sala(sala.getNome_sala());
        existente.setDuracao_aula(sala.getDuracao_aula());
        existente.setDuracao_intervalo(sala.getDuracao_intervalo());
        existente.setInicio_aula(sala.getInicio_aula());
        existente.setQuant_aula(sala.getQuant_aula());
    

        return repository.save(existente);
    }

    // DELETAR
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
