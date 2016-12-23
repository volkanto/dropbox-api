package org.tokmak.dropbox.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.tokmak.dropbox.data.DocumentTag;

public class DocumentTagRepositoryImpl implements DocumentTagCustomRepository {
	@Resource
	private SolrTemplate solrTemplate;

	@Override
	public List<DocumentTag> searchByTags(String[] tags) {
		SimpleQuery query = new SimpleQuery(this.createSearchConditions(tags));
		return this.solrTemplate.queryForPage(query, DocumentTag.class).getContent();
	}

	private Criteria createSearchConditions(String[] tags) {
		Criteria conditions = null;
		for (String tag : tags) {
			if (conditions == null) {
				conditions = new Criteria("tags_txt").contains(tag);
			} else {
				conditions = conditions.or(new Criteria("tags_txt").contains(tag));
			}
		}
		return conditions;
	}

}
