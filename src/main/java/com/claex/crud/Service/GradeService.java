package com.claex.crud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claex.crud.Entity.AulaEntity;
import com.claex.crud.Entity.GradeEntity;
import com.claex.crud.Entity.SalaEntity;
import com.claex.crud.Repository.GradeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GradeService {
    @Autowired
    private GradeRepository repository;
    
    public List<GradeEntity> listarTodos() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public GradeEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade não encontrado"));
    }

    // SALVAR
    public GradeEntity salvar(GradeEntity Grade) {
        return repository.save(Grade);
    }

    // ATUALIZAR
    public GradeEntity atualizar(Long id, GradeEntity grade) {

        GradeEntity existente = buscarPorId(id);

        existente.setData_grade(grade.getData_grade());
        existente.setSala(grade.getSala());

        return repository.save(existente);
    }

    // DELETAR
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // --------------- ALGORITMO GERAÇÃO DE GRADE ---------------

    public List<List<AulaEntity>> criarGrade(List<AulaEntity> materias, int quantAulas){
        List<AulaEntity> mats = new ArrayList<>(materias);
        List<List<AulaEntity>> grade = new ArrayList<>();
        Random random = new Random();

        // i vai de um a 5 representando dias da semana, pode ser alterado para uma variavel
        for(int i = 0; i < 5; i++){
            List<AulaEntity> dia = new ArrayList<>();
            for(int a = 0; a < quantAulas; a++){
                if(!mats.isEmpty()) {
                    int aleatorio = random.nextInt(mats.size());
                    dia.add(mats.remove(aleatorio));
                }
            }
            grade.add(dia);
        }
        return grade;
    }

    public List<List<AulaEntity>> buscarGrade(Long id){
        GradeEntity entity = buscarPorId(id);
        int quantAulas = entity.getSala().getQuant_aula();
        // setado como 5 pensando em dias da semana, porem pode ser uma variavel
        List<List<AulaEntity>> grade = new ArrayList<>(Collections.nCopies(5, Collections.nCopies(quantAulas, null)));
        AulaService service = new AulaService();
        List<AulaEntity> aulas = service.buscarPorGrade(id);

        for(AulaEntity aula : aulas){
            grade.get(aula.getDia()).set(aula.getPeriodo(), aula);
        }

        return grade;
    }

    public List<List<List<AulaEntity>>> buscarGradesEscola(Long id_sala){
        SalaService salaService = new SalaService();
        List<List<List<AulaEntity>>> gradesExistentes = new ArrayList<>();
        List<SalaEntity> salas = salaService.BuscarPorEscola(salaService.buscarPorId(id_sala).getEscola().getId());
        for(SalaEntity sala: salas){
            gradesExistentes.add(buscarGrade(sala.getId_sala()));
        }
        return gradesExistentes;
    }

    // --------------- AVALIAÇÃO DA GRADE ---------------

    // Possiveis otimizações: 
    // pode ser possivel tornar tudo uma unica classe;
    // Pode ser verificado se a grade atual já possui um conflito, do contrario não atravessa o for
    public int conflitos(List<List<AulaEntity>> gradeAnalisada, List<List<List<AulaEntity>>> gradesExistentes){
        int iguais = 0;
        for(List<List<AulaEntity>> grade : gradesExistentes){
            for(int i = 0; i<5; i++){
                for(int a = 0; a<gradeAnalisada.get(i).size(); a++){
                    if(gradeAnalisada.get(i).get(a) == grade.get(i).get(a)){
                        iguais += 1;
                    }
                }
            }
        }
        return iguais;
    }

    public int aulasSeguidas(List<List<AulaEntity>> grade){
        int total = 0;
        for(List<AulaEntity> dia : grade){
            for(int i=0;i<dia.size();i++){
                if(i != dia.size()-1 && dia.get(i).getId_materia() == dia.get(i+1).getId_materia() && i % 2 == 0){
                    total += 3;
                }
            }
        }
        return total;
    }

    public int aulasVagas(List<List<AulaEntity>> grade){
        int total = 0;
        for(List<AulaEntity> dia: grade){
            for(int i=0;i<0;i++){
                if(i==0 && dia.get(i).getId_materia() == null || i==dia.size()-1 && dia.get(i).getId_materia() == null){
                    total += 3;
                }
            }
        }
        return total;
    }

    // Criar metodo Limite Aulas Diarias posteriormente

    public int validarGrade(List<List<AulaEntity>> gradeAnalisada, List<List<List<AulaEntity>>> gradesExistentes, List<AulaEntity> materias){
        int pontos = 0;

        pontos += aulasSeguidas(gradeAnalisada);
        pontos += aulasVagas(gradeAnalisada);
        // pontos += limiteAulasDiarias();
        pontos += conflitos(gradeAnalisada, gradesExistentes);

        return pontos;
    }

    public List<List<AulaEntity>> mutarGrade(List<List<AulaEntity>> grade){
        Random random = new Random();

        int rand1 = random.nextInt(grade.size());
        int rand2 = random.nextInt(grade.get(0).size());
        
        int rand3 = random.nextInt(grade.size());
        int rand4 = random.nextInt(grade.get(0).size());

        AulaEntity apoio = grade.get(rand1).get(rand2);
        grade.get(rand1).set(rand2, grade.get(rand3).get(rand4));
        grade.get(rand3).set(rand4, apoio);

        return grade;
    }

    public List<List<AulaEntity>> avancarGeracao(List<List<AulaEntity>> gradeAnalisada, List<List<List<AulaEntity>>> gradesExistentes, List<AulaEntity> materias, int quantGeracoes, int quantIndv){
        List<List<AulaEntity>> melhorIndv = new ArrayList<>(gradeAnalisada);
        List<List<List<AulaEntity>>> listaIndv = new ArrayList<>();
        int melhorScore = validarGrade(gradeAnalisada, gradesExistentes, materias);

        for(int i=0; i<quantGeracoes; i++){
            for(int ind=0;ind<quantIndv;ind++){
                listaIndv.add(mutarGrade(melhorIndv));
            }
            for(List<List<AulaEntity>> indv: listaIndv){
                int indvScore = validarGrade(indv, gradesExistentes, materias);
                if(melhorScore < indvScore){
                    melhorIndv.clear();
                    for(List<AulaEntity> dia: indv){
                        melhorIndv.add(dia);
                    }
                    melhorScore=indvScore;
                }
            }
        }
        return melhorIndv;
    }

    public List<List<AulaEntity>> gerarGrade(List<List<List<AulaEntity>>> gradesExistentes, List<AulaEntity> materias, int quantGeracoes, int quantIndv, int quantAulas){
        return avancarGeracao(criarGrade(materias, quantAulas), gradesExistentes, materias, quantGeracoes, quantIndv);
    }

    public void salvarGrade(List<List<AulaEntity>> grade){
        AulaService aula = new AulaService();
        
        for(int dia = 0; dia<grade.size(); dia++){
            for(int periodo = 0; periodo<grade.get(dia).size(); periodo++){
                aula.salvar(grade.get(dia).get(periodo));
            }
        }
    }

    public void atualizarGrade(List<List<AulaEntity>> grade){
        AulaService aula = new AulaService();
        
        for(int dia = 0; dia<grade.size(); dia++){
            for(int periodo = 0; periodo<grade.get(dia).size(); periodo++){
                AulaEntity aulaEntity = grade.get(dia).get(periodo);
                aula.atualizar(aulaEntity.getId_aula(), aulaEntity);
            }
        }
    }
}
