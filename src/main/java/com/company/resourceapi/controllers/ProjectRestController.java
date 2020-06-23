package com.company.resourceapi.controllers;

import com.company.resourceapi.entities.Project;
import com.company.resourceapi.services.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(ProjectRestController.ENDPOINT)
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = "Project")
public class ProjectRestController {

	public static final String ENDPOINT = "/api/v2/projects";
	public static final String ENDPOINT_ID = "/{id}";
	public static final String PATH_VARIABLE_ID = "id";
	private static final String API_PARAM_ID = "ID";

	@Autowired
	private ProjectService projectService;

	@ApiOperation("Get a Project")
	@GetMapping(ENDPOINT_ID)
	public ResponseEntity<Project> getProject(
			@ApiParam(name = API_PARAM_ID, required = true)
			@PathVariable(PATH_VARIABLE_ID)
			final long projectId )
	{
		Project project = projectService.getProject(projectId);
		if (project == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().body(project);
	}

	@PostMapping()
	public ResponseEntity<Project> addProject(@Valid @RequestBody Project newProject) {

		Project response = null;
		try {
			response = projectService.addProject((newProject));
		}catch (DataIntegrityViolationException ex) { //violation of unique constraint
			return ResponseEntity.status(409).build();
		}

		if(response != null)
			return ResponseEntity.created(URI.create(ENDPOINT + "/" + newProject.getId())).body(response);
		else
			return	ResponseEntity.notFound().build();
	}

	@PatchMapping(ENDPOINT_ID)
	public ResponseEntity<Project> partialUpdateProject(@RequestBody Project partialUpdate, @ApiParam(name = API_PARAM_ID, required = true) @PathVariable(PATH_VARIABLE_ID) long projectId)
	{
		Project response = null;
		try {
			response = projectService.partialUpdate(partialUpdate, projectId);
		}catch (DataIntegrityViolationException ex) { //violation of unique constraint
			return ResponseEntity.status(409).build();
		}

		if(response != null)
			return ResponseEntity.ok().body(response);
		else
			return ResponseEntity.notFound().build();
	}
}
