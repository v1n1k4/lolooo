package com.claex.crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.claex.crud.Entity.CadastroEntity;
import com.claex.crud.Service.CadastroService;

@RestController
@RequestMapping("/form_cadastro-html")
public class CadastroHtmlController {

    @Autowired
    private CadastroService service;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarCadastro(
        @RequestParam String nome,
        @RequestParam String email,
        @RequestParam String senha,
        @RequestParam(required = false, defaultValue = "aluno") String tipo
    ) {
        CadastroEntity cadastro = new CadastroEntity();
        cadastro.setNome(nome);
        cadastro.setEmail(email);
        cadastro.setSenha(senha);
        cadastro.setTipo(tipo);
        service.salvar(cadastro);
        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(
        @PathVariable Long id,
        @RequestParam String nome,
        @RequestParam String senha,
        @RequestParam(required = false, defaultValue = "aluno") String tipo
    ) {
        CadastroEntity cadastro = service.buscarPorId(id);
        cadastro.setNome(nome);
        cadastro.setSenha(senha);
        cadastro.setTipo(tipo);
        service.salvar(cadastro);
        return ResponseEntity.ok("Atualizado com sucesso!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }
}
