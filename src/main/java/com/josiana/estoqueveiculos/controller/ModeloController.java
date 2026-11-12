package com.josiana.estoqueveiculos.controller;

import java.util.List;

import com.josiana.estoqueveiculos.model.Marca;
import com.josiana.estoqueveiculos.model.Modelo;
import com.josiana.estoqueveiculos.repository.MarcaRepository;
import com.josiana.estoqueveiculos.repository.ModeloRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public ModeloController(ModeloRepository modeloRepository,
            MarcaRepository marcaRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    @GetMapping
    public List<Modelo> listar() {
        return modeloRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Modelo cadastrar(@RequestBody Modelo dados) {

        if (dados.getNome() == null || dados.getNome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe o nome do modelo.");
        }

        if (dados.getMarca() == null || dados.getMarca().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe o id da marca.");
        }

        Marca marca = marcaRepository
                .findById(dados.getMarca().getId())
                .orElse(null);

        if (marca == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Marca não encontrada.");
        }

        Modelo modelo = new Modelo();
        modelo.setNome(dados.getNome().trim());
        modelo.setMarca(marca);

        return modeloRepository.save(modelo);
    }
}