package com.agile.feedback;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.models.Company;
import com.agile.feedback.models.Evaluation;
import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.models.Project;
import com.agile.feedback.models.TeamMember;

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

		udce.getProjects().addAll(Arrays.asList(edoc));

		TeamMember thiago = new TeamMember(null, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		thiago.getProjects().add(edoc);

		TeamMember pedro = new TeamMember(null, "Pedro", TeamMemberType.MANAGER, "pedro@email.com");
		pedro.getProjects().add(edoc);

		FeedbackForm feedback360 = new FeedbackForm(null, "360 Feedback", "360 Feedback", edoc, thiago);
		edoc.getFeedbackForms().add(feedback360);

		FeedbackItem technicalExperience = new FeedbackItem(null, "Technical Experience",
				"Level of Technical Experience", true, feedback360);
		FeedbackItem englishFluence = new FeedbackItem(null, "English Fluence", "Level of English Fluence", true,
				feedback360);

		feedback360.getItems().addAll(Arrays.asList(technicalExperience, englishFluence));

		Evaluation technicalThiagoSelfEvaluation = new Evaluation(null, 10.0, technicalExperience, thiago, thiago);
		Evaluation englishThiagoSelfEvaluation = new Evaluation(null, 8.0, englishFluence, thiago, thiago);
		Evaluation technicalPedroEvaluatedByThiago = new Evaluation(null, 8.0, technicalExperience, thiago, pedro);
		Evaluation englishPedroEvaluatedByThiago = new Evaluation(null, 6.0, englishFluence, thiago, pedro);
		Evaluation technicalThiagoEvaluatedByPedro = new Evaluation(null, 7.0, technicalExperience, pedro, thiago);
		Evaluation englishThiagoEvaluatedByPedro = new Evaluation(null, 6.0, englishFluence, pedro, thiago);
		Evaluation technicalPedroSelfEvaluation = new Evaluation(null, 9.0, technicalExperience, pedro, pedro);
		Evaluation englishPedroSelfEvaluation = new Evaluation(null, 8.5, englishFluence, pedro, pedro);

		technicalExperience.getItemEvaluations().addAll(Arrays.asList(technicalThiagoSelfEvaluation,
				technicalPedroEvaluatedByThiago, technicalPedroSelfEvaluation, technicalThiagoEvaluatedByPedro));
		englishFluence.getItemEvaluations().addAll(Arrays.asList(englishThiagoSelfEvaluation,
				englishPedroEvaluatedByThiago, englishPedroSelfEvaluation, englishThiagoEvaluatedByPedro));

		thiago.getEvaluationsAboutMe().addAll(Arrays.asList(technicalThiagoSelfEvaluation, englishThiagoSelfEvaluation,
				technicalThiagoEvaluatedByPedro, englishThiagoEvaluatedByPedro));
		pedro.getEvaluationsAboutMe().addAll(Arrays.asList(technicalPedroSelfEvaluation, englishPedroSelfEvaluation,
				technicalPedroEvaluatedByThiago, englishPedroEvaluatedByThiago));

//		companyRepository.saveAll(Arrays.asList(dataprev, udce));
//		projectRepository.saveAll(Arrays.asList(edoc, lifeProof));
//		teamMemberRepository.saveAll(Arrays.asList(eloy, thiago, pedro));
	}

}
