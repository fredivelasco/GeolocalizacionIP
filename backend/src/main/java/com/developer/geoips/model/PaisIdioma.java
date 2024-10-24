package com.developer.geoips.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


@Entity
@Table(name = "idioma_pais ", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisIdioma implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="sequenciaPaisIdioma")
    @SequenceGenerator(name="sequenciaPaisIdioma",sequenceName="idioma_pais_idioma_pais_id_seq", allocationSize=1)
    @Column(name = "idioma_pais_id", unique = true)
    @Basic(optional = false)
    private Long idiomaPaisId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id")
    @NotNull
    private Pais pais;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo")
    private String codigo;

}