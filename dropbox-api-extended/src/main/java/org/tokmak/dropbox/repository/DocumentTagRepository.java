package org.tokmak.dropbox.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.tokmak.dropbox.data.DocumentTag;

public interface DocumentTagRepository extends SolrCrudRepository<DocumentTag, String> {
	List<DocumentTag> findByName(String name);

//	@Query("name:?0")
//	@Facet(fields = { "tags_txt" }, limit = 5)
//	FacetPage<DocumentTag> findByNameAndFacetOnTags(String name, Pageable page);
	
//	@Query("tags:")
//	@Facet(fields = { "tags_txt" })
//	FacetPage<DocumentTag> findByTags(List<String> tags, Pageable pageable);
	
//	@Query("path:'?'")
//	DocumentTag findByPath(String path);
}
