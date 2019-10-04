package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.ProjectDTO;
import com.agile.feedback.exceptions.ProjectNotFoundException;
import com.agile.feedback.models.Project;
import com.agile.feedback.repositories.ProjectRepository;

@Service
public class ProjectService {

	Logger logger = LoggerFactory.getLogger(ProjectService.class);

	public static final String UPDATING_PROJECT = "Updating project: {0}";
	public static final String REMOVING_PROJECT = "Removing project, id: {0}";
	public static final String PROJECT_NOT_FOUND_FOR_ID = "Project not found for id ";
	public static final String FINDING_PROJECT_BY_ID = "Finding project, id: {0}";
	public static final String FINDING_ALL_PROJECTS = "Finding all projects";
	public static final String CREATING_A_PROJECT = "Creating a project: {0}";

	@Autowired
	private ProjectRepository repository;

	public Project create(Project project) {
		logger.info(CREATING_A_PROJECT, project);

		project.setId(null);
		return repository.save(project);
	}

	public List<Project> findAll() {
		logger.info(FINDING_ALL_PROJECTS);
		return repository.findAll();
	}

	public Project find(Integer projectId) {

		logger.info(FINDING_PROJECT_BY_ID, projectId);

		Optional<Project> optional = repository.findById(projectId);
		return optional.orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_FOR_ID + projectId));
	}

	public Project update(Project project) {
		logger.info(UPDATING_PROJECT, project);

		Project foundProject = find(project.getId());
		project.setCreatedAt(foundProject.getCreatedAt());

		return repository.save(project);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_PROJECT, id);

		find(id);
		repository.deleteById(id);
	}

	public Project fromDTO(@Valid ProjectDTO projectDto) {
		return new Project(projectDto.getId(), projectDto.getName());
	}
}
