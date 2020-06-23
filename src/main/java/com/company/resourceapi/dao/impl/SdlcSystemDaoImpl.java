package com.company.resourceapi.dao.impl;

import com.company.resourceapi.dao.SdlcSystemDao;
import com.company.resourceapi.entities.SdlcSystem;
import com.company.resourceapi.repositories.SdlcSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SdlcSystemDaoImpl implements SdlcSystemDao {

    @Autowired
    private SdlcSystemRepository sdlcSystemRepository;

    @Override
    public SdlcSystem getSdlcSystem(long id) {
        return sdlcSystemRepository.findById(id).orElse(null);
    }
}
