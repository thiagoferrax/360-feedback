package com.agile.feedback;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.models.Company;
import com.agile.feedback.models.Project;
import com.agile.feedback.repositories.CompanyRepository;
import com.agile.feedback.repositories.ProjectRepository;

@SpringBootApplication
public class FeedbackApplication implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FeedbackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Company dataprev = new Company(null, "Dataprev", CompanyType.HEAD_OFFICE, null);
		Company udce = new Company(null, "Dataprev - UDCE", CompanyType.BRANCH, dataprev);
		
		dataprev.getBranches().add(udce);
		
		Project edoc = new Project(null, "EDOC");
		edoc.getExecutingCompanies().add(udce);
		
		Project lifeProof = new Project(null, "LifeProof");
		lifeProof.getExecutingCompanies().add(udce);
		
		udce.getProjects().addAll(Arrays.asList(edoc, lifeProof));
		
		companyRepository.saveAll(Arrays.asList(dataprev, udce));
		
		projectRepository.saveAll(Arrays.asList(edoc, lifeProof));
		
	}

}
