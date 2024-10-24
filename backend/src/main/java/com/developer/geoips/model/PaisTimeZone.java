package com.developer.geoips.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


@Entity
@Table(name = "time_zone_pais ", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisTimeZone implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="sequenciaPaisTimeZone")
    @SequenceGenerator(name="sequenciaPaisTimeZone",sequenceName="time_zone_pais_time_zone_pais_id_seq", allocationSize=1)
    @Column(name = "time_zone_pais_id", unique = true)
    @Basic(optional = false)
    private Long timeZonePaisId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id")
    @NotNull
    private Pais pais;
    @Column(name = "time_zone")
    private String timeZone;
    @Column(name = "gmt")
    private String gmt;

}