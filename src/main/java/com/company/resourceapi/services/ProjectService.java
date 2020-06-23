package com.company.resourceapi.services;

import com.company.resourceapi.entities.Project;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

    Project getProject(long id);

    Project addProject(Project newProject);

    Project partialUpdate(Project partialUpdate, long id);
}
