package com.josiana.estoqueveiculos.repository;

import com.josiana.estoqueveiculos.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}