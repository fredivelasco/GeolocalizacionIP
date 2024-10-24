/*==============================================================*/
/* DATABASE: GeoIp_DB                                           */
/* Almacenamiento de información  IP                            */
/*==============================================================*/



/*==============================================================*/
/* Table: pais                                            */
/*==============================================================*/
create table pais (
   pais_id        serial4                 not null,
   codigo               int4                 null,
   nombre            char(80)             null,
   codigoISO         char(10)             null,
   latitud           double precision   null,
   longitud          double precision null,
   distancia         double precision null,
   moneda         char(10)             null,
   constraint pk_pais primary key (pais_id)
);

/*==============================================================*/
/* Table: ip_pais                                                 */
/*==============================================================*/
create table ip_pais  (
   ip_pais_id             serial4                 not null,
   pais_id        int8                 null,
   direccionIP               varchar(50)         null,
   constraint pk_ip_pais primary key (ip_pais_id)
);


/*==============================================================*/
/* Table: idioma_pais                                                 */
/*==============================================================*/
create table idioma_pais  (
   idioma_pais_id             serial4                 not null,
   pais_id        int8                 null,
   nombre              varchar(50)         null,
   codigo              varchar(50)         null,
   constraint pk_idioma_pais primary key (idioma_pais_id)
);

/*==============================================================*/
/* Table: time_zone_pais                                                */
/*==============================================================*/
create table time_zone_pais  (
   time_zone_pais_id             serial4                 not null,
   pais_id        int8                 null,
   time_Zone              varchar(50)         null,
   gmt              varchar(50)         null,
   constraint pk_time_zone_pais primary key (time_zone_pais_id  )
);


/*==============================================================*/
/* Table: estadistica                                                */
/*==============================================================*/
create table estadistica  (
   estadistica_id             serial4                 not null,
   codigo              varchar(50)         null,
   pais              varchar(80)         null,
   distancia         double precision null,
   invocaciones        int4                 null,
   constraint pk_estadistica primary key (estadistica_id )
);


alter table ip_pais
   add constraint fk_pais_r_001 foreign key (pais_id)
      references pais (pais_id)
      on delete restrict on update restrict;

alter table idioma_pais
   add constraint fk_pais_r_002 foreign key (pais_id)
      references pais (pais_id)
      on delete restrict on update restrict;

alter table time_zone_pais
   add constraint fk_pais_r_003 foreign key (pais_id)
      references pais (pais_id)
      on delete restrict on update restrict;