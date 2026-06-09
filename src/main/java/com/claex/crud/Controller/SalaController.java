package com.claex.crud.Controller;

import com.claex.crud.Entity.SalaEntity;
import com.claex.crud.Service.SalaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/salas")
public class SalaController {
    @Autowired
    private SalaService service;
    
    @GetMapping("/listar")
    public List<SalaEntity> listarTodos(){
        // @PathVariable Long id_escola
        return service.listarTodos();
        // return service.BuscarPorEscola(id_escola);
    }

    @GetMapping("/{id}")
    public SalaEntity buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping("/salvar")
    public SalaEntity salvar(@RequestBody SalaEntity sala){
        return service.salvar(sala);
    } 

    @PutMapping("/{id}")
    public SalaEntity atualizar(@PathVariable Long id ,@RequestBody SalaEntity sala){
        return service.atualizar(id, sala);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
