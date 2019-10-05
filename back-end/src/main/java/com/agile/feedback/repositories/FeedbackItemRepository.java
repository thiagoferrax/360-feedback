package com.agile.feedback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agile.feedback.models.FeedbackItem;

@Repository
public interface FeedbackItemRepository extends JpaRepository<FeedbackItem, Integer>{

}
