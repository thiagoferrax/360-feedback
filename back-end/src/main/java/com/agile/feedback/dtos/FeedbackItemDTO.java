package com.agile.feedback.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agile.feedback.models.FeedbackForm;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FeedbackItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	private String description;
	
	private Boolean ative;

	@JsonIgnore
	private FeedbackForm form;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAtive() {
		return ative;
	}

	public void setAtive(Boolean ative) {
		this.ative = ative;
	}

	public FeedbackForm getForm() {
		return form;
	}

	public void setForm(FeedbackForm form) {
		this.form = form;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ative == null) ? 0 : ative.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FeedbackItemDTO other = (FeedbackItemDTO) obj;
		if (ative == null) {
			if (other.ative != null) {
				return false;
			}
		} else if (!ative.equals(other.ative)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (form == null) {
			if (other.form != null) {
				return false;
			}
		} else if (!form.equals(other.form)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
