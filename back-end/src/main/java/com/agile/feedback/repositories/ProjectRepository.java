package com.agile.feedback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agile.feedback.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
