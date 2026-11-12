package com.josiana.estoqueveiculos.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class TratamentoErros {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> tratar(
            ResponseStatusException erro) {

        String mensagem = erro.getReason();

        if (mensagem == null) {
            mensagem = "Não foi possível concluir a operação.";
        }

        return ResponseEntity
                .status(erro.getStatusCode())
                .body(Map.of("mensagem", mensagem));
    }
}