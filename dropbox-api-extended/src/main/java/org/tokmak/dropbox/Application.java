package org.tokmak.dropbox;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

@PropertySource({ "file:config/application.properties" })
@SpringBootApplication(scanBasePackages = { "org.tokmak.dropbox" }, exclude = JpaRepositoriesAutoConfiguration.class)
@EnableSolrRepositories("org.tokmak.dropbox.repository")
public class Application extends SpringBootServletInitializer {

	private static final String EN_US = "en_US";

	@Value(value = "${solr.client.url}")
	private String solrClientUrl;

	@Value(value = "${dropbox.api.access-token}")
	private String dropboxAccessToken;

	@Value(value = "${dropbox.api.client-identifier}")
	private String clientIdentifier;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	@Bean
	public SolrClient solrServer() {
		return new HttpSolrClient(solrClientUrl);
	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient server) throws Exception {
		return new SolrTemplate(server);
	}

	@Bean
	public DbxClientV2 dropboxClient() {
		return new DbxClientV2(DbxRequestConfig.newBuilder(clientIdentifier).withUserLocale(EN_US).build(),
				dropboxAccessToken);
	}
}
