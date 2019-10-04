package com.agile.feedback.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agile.feedback.dtos.ProjectDTO;
import com.agile.feedback.models.Project;
import com.agile.feedback.services.ProjectService;

@RestController
@RequestMapping(value = "/projects")
public class ProjectResource {

	public static final String CREATING_NEW_PROJECT = "Creating a new project: {0}";
	public static final String UPDATING_PROJECT = "Updating project: {0}";
	public static final String REMOVING_PROJECT = "Removing project, id: {0}";
	public static final String GETTING_PROJECT_BY_ID = "Getting project by id: {0}";
	public static final String GETTING_ALL_PROJECTS = "Getting all project by id: {0}";

	Logger logger = LoggerFactory.getLogger(ProjectResource.class);

	@Autowired
	private ProjectService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<Project>> getAll() {

		logger.info(GETTING_ALL_PROJECTS);

		List<Project> projects = service.findAll();

		return ResponseEntity.ok().body(projects);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Project> create(@Valid @RequestBody ProjectDTO projectDto) {

		logger.info(CREATING_NEW_PROJECT, projectDto.getName());

		Project project = service.fromDTO(projectDto);
		Project newProject = service.create(project);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProject.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Project> findProjectById(@PathVariable Integer id) {

		logger.info(GETTING_PROJECT_BY_ID, id);

		Project project = service.find(id);

		return ResponseEntity.ok().body(project);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Project> update(@Valid @RequestBody ProjectDTO projectDto, @PathVariable Integer id) {

		logger.info(UPDATING_PROJECT, id);

		projectDto.setId(id);
		Project project = service.fromDTO(projectDto);

		service.update(project);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Project> delete(@PathVariable Integer id) {

		logger.info(REMOVING_PROJECT, id);

		service.remove(id);

		return ResponseEntity.noContent().build();
	}
}