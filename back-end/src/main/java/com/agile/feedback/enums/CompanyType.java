package com.agile.feedback.enums;

public enum CompanyType {
	HEAD_OFFICE(1, "Head Office"), BRANCH(2, "Branch");

	private final Integer codigo;
	private final String name;

	private CompanyType(Integer codigo, String name) {
		this.codigo = codigo;
		this.name = name;
	}

	public static CompanyType findByCodigo(Integer codigo) {
		CompanyType type = null;
		for (CompanyType element : values()) {
			if (element.getCodigo().equals(codigo)) {
				type = element;
				break;
			}
		}

		return type;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getName() {
		return name;
	}
}
