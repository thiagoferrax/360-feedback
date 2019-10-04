package com.agile.feedback.enums;

public enum TeamMemberType {
	MANAGER(1, "Manager"), DEVELOPER(2, "Developer");

	private final Integer codigo;
	private final String name;

	private TeamMemberType(Integer codigo, String name) {
		this.codigo = codigo;
		this.name = name;
	}

	public static TeamMemberType findByCodigo(Integer codigo) {
		TeamMemberType type = null;
		for (TeamMemberType element : values()) {
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
