package com.developer.geoips.service;


import com.developer.geoips.model.Estadistica;
import com.developer.geoips.repository.EstadisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    @Autowired
    private EstadisticaRepository estadisticaRepository;

    @Override
    public List<Estadistica> findAll() {


        return estadisticaRepository.findAll();

    }

    @Override
    public Optional<Estadistica> findById(Long estadisticaId) {
        return estadisticaRepository.findById(estadisticaId);
    }

    @Override
    public Estadistica save(Estadistica entity) throws Exception {
        return estadisticaRepository.save(entity);
    }

    @Override
    public Estadistica update(Estadistica entity) throws Exception {
        return estadisticaRepository.save(entity);
    }

    @Override
    public void delete(Estadistica entity) throws Exception {
        estadisticaRepository.deleteById(entity.getEstadisticaId());

    }

    @Override
    public void deleteById(Long id) throws Exception {
        estadisticaRepository.deleteById(id);
    }

    @Override
    public void validate(Estadistica entity) throws Exception {

    }

    @Override
    public Long count() {
        return estadisticaRepository.count();
    }


    @Override
    public Optional<Estadistica> findByCodigo(String codigo) {
        return estadisticaRepository.findByCodigo(codigo);
    }
}
