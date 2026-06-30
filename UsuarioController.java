package com.exemplo.projeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CodigoController {

    @PostMapping("/validar")
    public ResponseEntity<String> validarCodigo(@RequestBody ValidacaoDTO requisicao) {
        
        // Validação de segurança robusta no servidor (Java)
        if (requisicao.getCodigo() == null || !requisicao.getCodigo().matches("\\d+")) {
            // Aprendizado 2: Retorna o erro em formato JSON estruturado (HTTP 400) sem quebrar o front-end
            return ResponseEntity.badRequest().body("{\"erro\": \"Formato inválido. Insira apenas números.\"}");
        }
        
        // Simulação de verificação de regra de negócio (ex: se o código expirou ou não existe)
        boolean codigoInvalido = "00000".equals(requisicao.getCodigo()); 
        if (codigoInvalido) {
            return ResponseEntity.badRequest().body("{\"erro\": \"Este código expirou ou não existe.\"}");
        }

        // Se o código for aceito pelo sistema, retorna sucesso (HTTP 200)
        return ResponseEntity.ok("{\"status\": \"Valido\"}");
    }
}

// Classe DTO (Data Transfer Object) para mapear o JSON recebido do JavaScript
class ValidacaoDTO {
    private String codigo;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}