package com.agile.feedback;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.models.Company;
import com.agile.feedback.repositories.CompanyRepository;

@SpringBootApplication
public class FeedbackApplication implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FeedbackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Company dataprev = new Company(null, "Dataprev", CompanyType.HEAD_OFFICE, null);
		Company udce = new Company(null, "Dataprev - UDCE", CompanyType.BRANCH, dataprev);
		
		dataprev.getBranches().add(udce);
		
		companyRepository.saveAll(Arrays.asList(dataprev, udce));
	}

}
