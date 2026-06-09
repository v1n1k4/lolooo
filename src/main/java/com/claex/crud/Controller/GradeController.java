package com.claex.crud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claex.crud.Entity.AulaEntity;
import com.claex.crud.Entity.GradeEntity;
import com.claex.crud.Service.GradeService;
import com.claex.crud.Service.SalaService;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService service;

    @GetMapping("/buscar/{id}")
    public List<List<AulaEntity>> buscarGrade(@PathVariable Long id){
        return service.buscarGrade(id);
    }

    @PostMapping("/gerargrade/{id_sala}")
    public void gerarGrade(@PathVariable Long id_sala, @RequestBody List<AulaEntity> materias){
        GradeEntity grade = new GradeEntity();
        SalaService sala = new SalaService();
        grade.setSala(sala.buscarPorId(id_sala));
        GradeEntity gradeEntity = service.salvar(grade);
        List<List<List<AulaEntity>>> gradesExistentes = service.buscarGradesEscola(gradeEntity.getSala().getId_sala());
        
        List<List<AulaEntity>> gradeFinalizada = service.gerarGrade(gradesExistentes, materias, 50, 50, gradeEntity.getSala().getQuant_aula());

        service.salvarGrade(gradeFinalizada);
        // return gradeFinalizada;
    }
}
