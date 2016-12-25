package org.tokmak.dropbox.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.tokmak.dropbox.data.DocumentTag;

public interface DocumentTagRepository extends SolrCrudRepository<DocumentTag, String> {
	List<DocumentTag> findByName(String name);
}
