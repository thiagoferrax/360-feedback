package com.agile.feedback.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.agile.feedback.exceptions.ProjectNotFoundException;
import com.agile.feedback.models.Project;
import com.agile.feedback.repositories.ProjectRepository;

public class ProjectServiceTest {

	@InjectMocks
	private ProjectService projectService;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenIdExistsReturnsProject() {
		// Given
		Integer existingId = 1;

		Project existingProject = new Project();
		existingProject.setId(existingId);

		Optional<Project> optional = Optional.of(existingProject);
		Mockito.when(projectRepository.findById(existingId)).thenReturn(optional);

		// When
		Project project = projectService.find(existingId);

		// Then
		Assert.assertEquals(existingProject, project);
	}

	@Test(expected = ProjectNotFoundException.class)
	public void whenIdDoesNotExistThrowsProjectNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(projectRepository.findById(notExistingId))
				.thenThrow(new ProjectNotFoundException(ProjectService.PROJECT_NOT_FOUND_FOR_ID + notExistingId));

		// When
		projectService.find(notExistingId);
	}

	@Test
	public void whenUpdatinAProjectVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		Project existingProject = new Project();
		existingProject.setId(existingId);

		Optional<Project> optional = Optional.of(existingProject);
		Mockito.when(projectRepository.findById(existingId)).thenReturn(optional);

		// When
		projectService.update(existingProject);

		// Then
		Mockito.verify(projectRepository).save(existingProject);
	}

	@Test
	public void whenDeletingAProjectVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		Project existingProject = new Project();
		existingProject.setId(existingId);

		Optional<Project> optional = Optional.of(existingProject);
		Mockito.when(projectRepository.findById(existingId)).thenReturn(optional);

		// When
		projectService.remove(existingId);

		// Then
		Mockito.verify(projectRepository).deleteById(existingId);
	}

}
