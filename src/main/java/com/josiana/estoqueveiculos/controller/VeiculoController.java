package com.josiana.estoqueveiculos.controller;

import java.util.List;

import com.josiana.estoqueveiculos.model.Veiculo;
import com.josiana.estoqueveiculos.repository.VeiculoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;

import com.josiana.estoqueveiculos.model.Modelo;
import com.josiana.estoqueveiculos.repository.ModeloRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

import com.josiana.estoqueveiculos.model.StatusVeiculo;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;

    public VeiculoController(VeiculoRepository veiculoRepository,
            ModeloRepository modeloRepository) {
        this.veiculoRepository = veiculoRepository;
        this.modeloRepository = modeloRepository;
    }

    @GetMapping
    public List<Veiculo> listar(
            @RequestParam(value = "marcaId", required = false) Long marcaId,
            @RequestParam(value = "modeloId", required = false) Long modeloId,
            @RequestParam(value = "precoMin", required = false) BigDecimal precoMin,
            @RequestParam(value = "precoMax", required = false) BigDecimal precoMax,
            @RequestParam(value = "ano", required = false) Integer ano,
            @RequestParam(value = "status", required = false) StatusVeiculo status) {

        if ((precoMin != null && precoMin.signum() < 0)
                || (precoMax != null && precoMax.signum() < 0)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Os preços dos filtros não podem ser negativos.");
        }

        if (precoMin != null && precoMax != null
                && precoMin.compareTo(precoMax) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O preço mínimo não pode ser maior que o máximo.");
        }

        return veiculoRepository.filtrar(
                marcaId, modeloId, precoMin, precoMax, ano, status);
    }

    @GetMapping("/{id}")
    public Veiculo buscarPorId(@PathVariable("id") Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Veículo não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@RequestBody Veiculo dados) {

        if (dados.getModelo() == null || dados.getModelo().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe o id do modelo.");
        }

        if (dados.getAnoFabricacao() == null
                || dados.getAnoFabricacao() <= 0
                || dados.getAnoFabricacao() > Year.now().getValue()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe um ano de fabricação válido.");
        }

        if (dados.getCor() == null || dados.getCor().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe a cor.");
        }

        if (dados.getPreco() == null || dados.getPreco().signum() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O preço deve ser maior que zero.");
        }

        if (dados.getQuilometragem() == null
                || dados.getQuilometragem() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A quilometragem não pode ser negativa.");
        }

        if (dados.getStatus() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe o status.");
        }

        Modelo modelo = modeloRepository
                .findById(dados.getModelo().getId())
                .orElse(null);

        if (modelo == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Modelo não encontrado.");
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modelo);
        veiculo.setAnoFabricacao(dados.getAnoFabricacao());
        veiculo.setCor(dados.getCor().trim());
        veiculo.setPreco(dados.getPreco());
        veiculo.setQuilometragem(dados.getQuilometragem());
        veiculo.setStatus(dados.getStatus());

        return veiculoRepository.save(veiculo);
    }

    @PatchMapping("/{id}")
    public Veiculo atualizar(@PathVariable("id") Long id,
            @RequestBody Veiculo dados) {

        Veiculo veiculo = buscarPorId(id);

        if (dados.getPreco() == null
                && dados.getQuilometragem() == null
                && dados.getStatus() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe preço, quilometragem ou status para atualizar.");
        }

        if (dados.getPreco() != null && dados.getPreco().signum() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O preço deve ser maior que zero.");
        }

        if (dados.getQuilometragem() != null
                && dados.getQuilometragem() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A quilometragem não pode ser negativa.");
        }

        if (dados.getPreco() != null) {
            veiculo.setPreco(dados.getPreco());
        }

        if (dados.getQuilometragem() != null) {
            veiculo.setQuilometragem(dados.getQuilometragem());
        }

        if (dados.getStatus() != null) {
            veiculo.setStatus(dados.getStatus());
        }

        return veiculoRepository.save(veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") Long id) {
        Veiculo veiculo = buscarPorId(id);
        veiculoRepository.delete(veiculo);
    }
}