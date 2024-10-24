package com.developer.geoips.service;

import com.developer.geoips.model.Pais;
import com.developer.geoips.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public List<Pais> findAll() {

        return paisRepository.findAll();
    }

    @Override
    public Optional<Pais> findById(Long paisId) {
        return paisRepository.findById(paisId);
    }

    @Override
    public Pais save(Pais entity) throws Exception {
        return paisRepository.save(entity);
    }

    @Override
    public Pais update(Pais entity) throws Exception {
        return paisRepository.save(entity);
    }

    @Override
    public void delete(Pais entity) throws Exception {
        paisRepository.deleteById(entity.getPaisId());

    }

    @Override
    public void deleteById(Long id) throws Exception {
        paisRepository.deleteById(id);
    }

    @Override
    public void validate(Pais entity) throws Exception {

    }

    @Override
    public Long count() {
        return paisRepository.count();
    }


    @Override
    public Optional<Pais> findByCodeISO(String countryCode) {
        return paisRepository.findByCodigo(countryCode);
    }
}
