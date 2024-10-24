package com.developer.geoips.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serial;


@Entity
@Table(name = "ip_pais ", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisIP implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="sequenciaPaisIP")
    @SequenceGenerator(name="sequenciaPaisIP",sequenceName="ip_pais_ip_pais_id_seq", allocationSize=1)
    @Column(name = "ip_pais_id", unique = true)
    @Basic(optional = false)
    private Long ipPaisId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "pais_id")
    @NotNull
    private Pais pais;
    @Column(name = "direccionip")
    private String direccionIP;

}