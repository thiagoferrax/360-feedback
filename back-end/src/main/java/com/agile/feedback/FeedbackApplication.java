package com.agile.feedback;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.models.Company;
import com.agile.feedback.models.Project;
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.repositories.CompanyRepository;
import com.agile.feedback.repositories.ProjectRepository;
import com.agile.feedback.repositories.TeamMemberRepository;

@SpringBootApplication
public class FeedbackApplication implements CommandLineRunner {

//	@Autowired
//	private CompanyRepository companyRepository;
//
//	@Autowired
//	private ProjectRepository projectRepository;
//
//	@Autowired
//	private TeamMemberRepository teamMemberRepository;

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

		TeamMember eloy = new TeamMember(null, "Eloy", TeamMemberType.DEVELOPER, "eloy@email.com");
		eloy.getProjects().add(edoc);

		TeamMember pedro = new TeamMember(null, "Pedro", TeamMemberType.MANAGER, "pedro@email.com");
		pedro.getProjects().addAll(Arrays.asList(edoc, lifeProof));

		TeamMember thiago = new TeamMember(null, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		thiago.getProjects().add(lifeProof);

		edoc.getMembers().addAll(Arrays.asList(eloy, pedro));
		lifeProof.getMembers().addAll(Arrays.asList(thiago, pedro));

//		companyRepository.saveAll(Arrays.asList(dataprev, udce));
//		projectRepository.saveAll(Arrays.asList(edoc, lifeProof));
//		teamMemberRepository.saveAll(Arrays.asList(eloy, thiago, pedro));
	}

}
