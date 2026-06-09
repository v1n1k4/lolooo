package com.claex.crud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claex.crud.Entity.ProfessorEntity;
import com.claex.crud.Service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorControler {
    @Autowired
    private ProfessorService service;

    @GetMapping("/listar")
    public List<ProfessorEntity> listarTodos(){
        return service.listarTodos();
    }
}
