package com.agile.feedback.builders;

import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.models.TeamMember;

public class TeamMemberBuilder {
	private TeamMember teamMember;

	private TeamMemberBuilder() {
	}
	
	public static TeamMemberBuilder newTeamMember() {
		TeamMemberBuilder builder = new TeamMemberBuilder();
		builder.teamMember = new TeamMember();
		return builder;
	}

	public TeamMemberBuilder withId(Integer id) {
		teamMember.setId(id);
		return this;
	}
	
	public TeamMemberBuilder withName(String name) {
		teamMember.setName(name);
		return this;
	}
	
	public TeamMemberBuilder withType(TeamMemberType type) {
		teamMember.setType(type);
		return this;
	}
	
	public TeamMemberBuilder withEmail(String email) {
		teamMember.setEmail(email);
		return this;
	}


	public TeamMember now() {
		return teamMember;
	}
}
