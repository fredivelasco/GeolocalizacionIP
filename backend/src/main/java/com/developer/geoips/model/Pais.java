package com.developer.geoips.model;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.ArrayList;

import java.util.List;


@Entity
@Table(name = "pais", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="sequenciaPais")
    @SequenceGenerator(name="sequenciaPais",sequenceName="pais_pais_id_seq", allocationSize=1)
    @Column(name = "pais_id", unique = true)
    @Basic(optional = false)
    private Long paisId;
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigoISO")
    private String codigoIso;
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "distancia")
    private Double distancia;
    @Column(name = "moneda")
    private String moneda;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaisIdioma> idiomas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    private List<PaisTimeZone> timeZones = new ArrayList<>();


    public void addIdioma(PaisIdioma idioma) {
        idioma.setPais(this);
        this.idiomas.add(idioma);
    }


}