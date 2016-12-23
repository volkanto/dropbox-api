package org.tokmak.dropbox.data;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class DocumentTag {
	@Id
	@Field
	private String id;

	@Field
	private String path;

	@Field
	private String name;

	@Field("tags_txt")
	private List<String> tags;
	
	public DocumentTag() { }
	
	public DocumentTag(List<String> tags, String path) {
		super();
		this.tags = tags;
		this.path = path;
	}
	
	public DocumentTag(List<String> tags, String path, String name) {
		super();
		this.tags = tags;
		this.path = path;
		this.name = name;
	}

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

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DocumentTag [id=" + this.id + ", tags=" + this.tags + ", path=" + this.path + ", name=" + this.name
				+ "]";
	}

}