package com.developer.geoips.service;


import com.developer.geoips.model.PaisIP;
import com.developer.geoips.repository.PaisIPRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaisIPServiceImpl implements PaisIPService {

    @Autowired
    private PaisIPRepository paisIPRepository;

    @Override
    public List<PaisIP> findAll() {

        return paisIPRepository.findAll();
    }

    @Override
    public Optional<PaisIP> findById(Long paisId) {
        return paisIPRepository.findById(paisId);
    }

    @Override
    public PaisIP save(PaisIP entity) throws Exception {
        return paisIPRepository.save(entity);
    }

    @Override
    public PaisIP update(PaisIP entity) throws Exception {
        return paisIPRepository.save(entity);
    }

    @Override
    public void delete(PaisIP entity) throws Exception {
        paisIPRepository.deleteById(entity.getIpPaisId());

    }

    @Override
    public void deleteById(Long id) throws Exception {
        paisIPRepository.deleteById(id);
    }

    @Override
    public void validate(PaisIP entity) throws Exception {

    }

    @Override
    public Long count() {
        return paisIPRepository.count();
    }


    @Override
    public Optional<PaisIP> findByDireccionIp(String direccionIp) {
        return paisIPRepository.findByDireccionIp(direccionIp);
    }
}
