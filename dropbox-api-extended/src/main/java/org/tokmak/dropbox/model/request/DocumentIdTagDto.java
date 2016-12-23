package org.tokmak.dropbox.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;

public class DocumentIdTagDto {
	@NotNull
	String id;
	@NotNull
	List<String> tags;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
