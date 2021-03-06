package com.agile.feedback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agile.feedback.models.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{

}
