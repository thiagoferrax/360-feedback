package com.agile.feedback.builders;

import com.agile.feedback.models.Project;

public class ProjectBuilder {
	private Project project;

	private ProjectBuilder() {
	}
	
	public static ProjectBuilder newProject() {
		ProjectBuilder builder = new ProjectBuilder();
		builder.project = new Project();
		return builder;
	}

	public ProjectBuilder withId(Integer id) {
		project.setId(id);
		return this;
	}
	
	public ProjectBuilder withName(String name) {
		project.setName(name);
		return this;
	}

	public Project now() {
		return project;
	}
}
