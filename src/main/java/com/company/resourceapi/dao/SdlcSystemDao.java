package com.company.resourceapi.dao;

import com.company.resourceapi.entities.SdlcSystem;
import org.springframework.stereotype.Repository;

@Repository
public interface SdlcSystemDao {

    public SdlcSystem getSdlcSystem(long id);
}
