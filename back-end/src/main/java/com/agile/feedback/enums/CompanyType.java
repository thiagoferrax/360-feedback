package com.agile.feedback.enums;

public enum CompanyType {
	HEAD_OFFICE(1, "Head Office"), BRANCH(2, "Branch");

	private final Integer id;
	private final String name;

	private CompanyType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static CompanyType findById(Integer id) {
		CompanyType type = null;
		for (CompanyType element : values()) {
			if (element.getId().equals(id)) {
				type = element;
				break;
			}
		}

		return type;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
