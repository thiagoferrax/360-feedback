package com.agile.feedback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agile.feedback.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
