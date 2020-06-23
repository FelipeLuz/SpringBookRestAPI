package com.company.resourceapi.services.impl;

import com.company.resourceapi.dao.ProjectDao;
import com.company.resourceapi.dao.SdlcSystemDao;
import com.company.resourceapi.entities.Project;
import com.company.resourceapi.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private SdlcSystemDao sdlcSystemDao;

    @Override
    public Project getProject(long id) {
        return this.projectDao.getProject(id);
    }

    @Override
    public Project addProject(Project newProject) {
        if(sdlcSystemExists(newProject.getSdlcSystem().getId())) {
            newProject.setCreatedDate(Instant.now());
            newProject.setLastModifiedDate(Instant.now());
            return this.projectDao.save(newProject);
        }
        else
            return null;
    }

    @Override
    public Project partialUpdate(Project partialUpdate, long id) {
        Project originalProject = this.getProject(id);
        if(originalProject != null)
        {
            Project updatedProject = new Project();
            updatedProject.setId(id);
            updatedProject.setCreatedDate(originalProject.getCreatedDate());
            updatedProject.setLastModifiedDate(Instant.now());
            updatedProject.setExternalId(partialUpdate.getExternalId() != null ? partialUpdate.getExternalId() : originalProject.getExternalId());
            updatedProject.setName(partialUpdate.getName() != "" ? partialUpdate.getName() : originalProject.getName());
            updatedProject.setSdlcSystem(partialUpdate.getSdlcSystem() != null ? partialUpdate.getSdlcSystem() : originalProject.getSdlcSystem());

            if(sdlcSystemExists(updatedProject.getSdlcSystem().getId()))
                return this.projectDao.save(updatedProject);
            else
                return null;
        }
        else
            return null;
    }

    private boolean sdlcSystemExists(long id)
    {
        return sdlcSystemDao.getSdlcSystem(id) != null;
    }
}
