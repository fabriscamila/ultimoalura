package com.exemplo.projeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        
        // Validação de segurança robusta no servidor (Java)
        if (usuario.getCodigo() == null || !usuario.getCodigo().matches("\\d+")) {
            // Aprendizado 2: Retorna o erro em formato JSON estruturado (HTTP 400) em vez de quebrar a tela
            return ResponseEntity.badRequest().body("{\"erro\": \"Código inválido. Digite apenas números.\"}");
        }
        
        // Simulação de validação no banco de dados (ex: se o código já existe)
        boolean codigoExisteNoBanco = false; 
        if (codigoExisteNoBanco) {
            return ResponseEntity.badRequest().body("{\"erro\": \"Este código já está em uso no sistema.\"}");
        }

        // Se passar nas regras, retorna sucesso (HTTP 200)
        return ResponseEntity.ok("{\"status\": \"Sucesso\"}");
    }
}

// Classe DTO (Data Transfer Object) para mapear o JSON recebido do JavaScript
class UsuarioDTO {
    private String codigo;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}