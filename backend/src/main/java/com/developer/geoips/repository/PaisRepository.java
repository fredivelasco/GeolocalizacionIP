package com.developer.geoips.repository;

import com.developer.geoips.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PaisRepository extends JpaRepository<Pais, Long> {
    @Query("SELECT p from Pais p where p.codigoIso = :codigoIso")
    Optional<Pais> findByCodigo(@Param("codigoIso") String codigoIso);
}
