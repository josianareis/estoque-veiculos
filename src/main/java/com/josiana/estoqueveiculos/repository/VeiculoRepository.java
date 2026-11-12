package com.josiana.estoqueveiculos.repository;

import java.math.BigDecimal;
import java.util.List;

import com.josiana.estoqueveiculos.model.StatusVeiculo;
import com.josiana.estoqueveiculos.model.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByStatus(StatusVeiculo status);

    @Query("""
            SELECT v FROM Veiculo v
            WHERE (:marcaId IS NULL OR v.modelo.marca.id = :marcaId)
              AND (:modeloId IS NULL OR v.modelo.id = :modeloId)
              AND (:precoMin IS NULL OR v.preco >= :precoMin)
              AND (:precoMax IS NULL OR v.preco <= :precoMax)
              AND (:ano IS NULL OR v.anoFabricacao = :ano)
              AND (:status IS NULL OR v.status = :status)
            """)
    List<Veiculo> filtrar(
            @Param("marcaId") Long marcaId,
            @Param("modeloId") Long modeloId,
            @Param("precoMin") BigDecimal precoMin,
            @Param("precoMax") BigDecimal precoMax,
            @Param("ano") Integer ano,
            @Param("status") StatusVeiculo status);
}