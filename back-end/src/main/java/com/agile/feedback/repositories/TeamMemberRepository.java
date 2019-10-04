package com.agile.feedback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agile.feedback.models.Project;
import com.agile.feedback.models.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer>{

}
