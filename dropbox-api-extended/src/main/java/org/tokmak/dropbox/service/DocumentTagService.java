package org.tokmak.dropbox.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;
import org.tokmak.dropbox.data.DocumentTag;
import org.tokmak.dropbox.model.request.DocumentTagDto;
import org.tokmak.dropbox.repository.DocumentTagRepository;
import org.tokmak.dropbox.repository.DocumentTagRepositoryImpl;

@Service
public class DocumentTagService {
	@Autowired
	private Logger logger;
	@Autowired
	private DocumentTagRepository tagRepository;
	@Autowired
	private DocumentTagRepositoryImpl customTagRepository;

	public boolean createNewDocument(String name, String path, List<String> tags) {
		try {
			DocumentTag documentTag = new DocumentTag();
			documentTag.setName(name);
			documentTag.setPath(path);
			documentTag.setTags(this.makeTagsLowerCase(tags));
			this.tagRepository.save(documentTag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	private List<String> makeTagsLowerCase(List<String> tags) {
		if(tags != null && !tags.isEmpty()) {
			return tags.stream().map(s -> {
				return s.toLowerCase();
			}).collect(Collectors.toList());
		}
		return null;
	}

	public List<DocumentTag> searchDocumentByTag(String[] tags, Pageable page) {
		return this.customTagRepository.searchByTags(tags, page);
	}

	public boolean deleteGivenTags(String id, List<String> tags) {
		DocumentTag documentToUpdate = this.getDocumentBy(id);
		if (documentToUpdate != null) {
			List<String> documentTags = documentToUpdate.getTags();
			if (documentTags != null && !documentTags.isEmpty()) {
				for (String tag : tags) {
					if (documentTags.contains(tag)) {
						documentTags.remove(tag);
					}
				}
				documentToUpdate.setTags(documentTags);
				this.tagRepository.save(documentToUpdate);
			}
			return true;
		}
		return false;
	}

	public boolean updateGivenTags(String id, List<String> tags) {
		DocumentTag documentToUpdate = this.getDocumentBy(id);
		if (documentToUpdate != null) {
			List<String> existingTags = documentToUpdate.getTags();
			if (existingTags != null && !existingTags.isEmpty()) {
				for (String tag : tags) {
					if (!existingTags.contains(tag)) {
						existingTags.add(tag.toLowerCase());
					}
				}
				documentToUpdate.setTags(existingTags);
			} else {
				documentToUpdate.setTags(tags);
			}
			this.tagRepository.save(documentToUpdate);
			return true;
		}
		return false;
	}

	private DocumentTag getDocumentBy(String id) {
		return this.tagRepository.findOne(id);
	}

	public List<DocumentTag> listAllDocuments() {
		SolrResultPage<DocumentTag> result = (SolrResultPage<DocumentTag>) this.tagRepository.findAll();
		if (result != null) {
			return result.getContent();
		}
		return Arrays.asList();
	}

	public void indexDocuments(List<DocumentTagDto> allDocumentsWithPath) {
		for (DocumentTagDto document : allDocumentsWithPath) {
			this.createNewDocument(document.getDocumentName(), document.getDocumentPath(), null);
		}
	}

	public void removeAllDocuments() {
		this.tagRepository.deleteAll();
	}
}
