package com.developer.geoips.repository;

import com.developer.geoips.model.PaisIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PaisIPRepository extends JpaRepository<PaisIP, Long> {
    @Query("SELECT d from PaisIP d where d.direccionIP = :direccionIP")
    Optional<PaisIP> findByDireccionIp(@Param("direccionIP") String direccionIp);
}
