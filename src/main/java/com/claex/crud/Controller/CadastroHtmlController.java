package com.claex.crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claex.crud.Entity.CadastroEntity;
import com.claex.crud.Service.CadastroService;
@Controller
@RequestMapping("/form_cadastro-html")
public class CadastroHtmlController {
  @Autowired
    private CadastroService service;

    // CREATE - salva no banco e retorna JSON para o fetch do HTML
    @PostMapping("/salvar")
    @ResponseBody
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

    // LIST - lista todos os cadastros
    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}

