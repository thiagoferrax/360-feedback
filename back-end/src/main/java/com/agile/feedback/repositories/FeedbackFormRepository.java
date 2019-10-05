package com.agile.feedback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agile.feedback.models.FeedbackForm;

@Repository
public interface FeedbackFormRepository extends JpaRepository<FeedbackForm, Integer>{

}
