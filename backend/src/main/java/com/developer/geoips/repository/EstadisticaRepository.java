package com.developer.geoips.repository;

import com.developer.geoips.model.Estadistica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {
    @Query("SELECT e from Estadistica e where e.codigo = :codigo")
    Optional<Estadistica> findByCodigo(@Param("codigo")String codigo);
}
