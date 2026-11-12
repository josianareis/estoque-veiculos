package com.josiana.estoqueveiculos.repository;

import com.josiana.estoqueveiculos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}