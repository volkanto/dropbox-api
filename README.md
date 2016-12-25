# Dropbox API - Extended
### About
- - - -
Dropbox API - Extended is a simple RESTful application that you can add, delete or search tags related with your documents on Dropbox over Dropbox API. 

#### Task
* We ask you to build a Spring Boot application, which is a Rest API that extends the Dropbox API functionality by introducing “tags”.
* Tags are lowercase, alphanumeric strings that can contain useful information about a file, such as "beach", "miami" for a photo, or "work", "cv", "application" for a job application document.

#### API consumer should be able to:
* Add any number of tags/remove them using the API.
* Search files by a specific tag or multiple tags (with AND or OR connection, preferably both). The search should preferably support pagination.
* -The API should also allow consumer to download all files that have some specific tag(s) by zipping them together into a single file. The summed size of the files should be validated to be below 500MB (can be configurable externally as well) for this endpoint.-

#### Technical Requirements:
* The Dropbox API client app that your application will use should be externally configurable - means that we should be able to pass an app key and secret of our the Dropbox App to your app externally (i.e. via environment variables), and the application should make use of them and use that app, not a built-in or hardcoded one.
* Tags should be indexed in an Apache SOLR instance. Its URL also should be configurable externally.
* A docker image should be built on app build phases, and we should be able to simply run a docker container from your image to test the app (instructions on how to run your app would be nice). (A docker-compose file which spawns everything up&ready to test would be extra nice)
* The API should follow RESTful API conventions & best practices.

### Requirements
- - - -
Docker and docker compose in your local or virtual machine. And also Solr instance.

If you are using macOS, you can download from this link [Get started with Docker for Mac - Docker](https://docs.docker.com/docker-for-mac/) .

You can follow instructions for Ubuntu 16.04 from this link [How To Install Docker Compose on Ubuntu 16.04 | DigitalOcean](https://www.digitalocean.com/community/tutorials/how-to-install-docker-compose-on-ubuntu-16-04).

### Solr configuration
- - - -
You need to create `schema.xml` under `<solr-home>/server/solr/dropbox-core/conf` folder. And you have to state field definitions like this:
```
<field name="name" type="text_general" indexed="true" stored="true" />
<field name="path" type="text_general" indexed="true" stored="true"/>
```

### How to run
- - - -
`git clone https://github.com/volkanto/dropbox-api.git`

Go to `dropbox-api/dropbox-api-docker/dropbox-api/content/config` folder and change `application.properties` file with your settings.

You need to change lines below:
`dropbox.api.access-token=<your-dropbox-access-token>` -> with your access token
`solr.client.url=http://<solr-url>:<solr-port>/solr/<your-solr-core>` -> with your Solr instance url:port and core.

Lastly,

You have to change/create folder to extract logs file to external folder. 

If you want to do this one, change line which is stated below in `docker-compose.yml` file:
> `<your-log-folder-path>:/data/log`  

If you don’t want to extract logs, please remove these two lines:
```
volumes:
	  - <your-log-folder-path>:/data/log
```

### How to use
> Create tags  
```
curl -i -H "Content-Type: application/json" -X POST -d '{"documentName":"holiday", "documentPath":"/books/holiday.txt", "descriptions":["miami","beach"]}' http://localhost:8081/api/tags
```

> Delete tags  
```
curl -i -H "Content-Type: application/json" -X DELETE -d '{"id":"4f415794-b0ac-45a4-91ac-16669b51dac9","tags":["microservices", "nodejs"]}' http://localhost:8081/api/tags
```

> Update tags  
```
curl -i -H "Content-Type: application/json" -X PUT -d '{"id":"4f415794-b0ac-45a4-91ac-16669b51dac9","tags":["microservices", "nodejs"]}' http://localhost:8081/api/tags'
```

> Search tags  
```
curl -i -H "Content-Type: application/json" -X GET http://localhost:8081/api/tags?tags=miami,beach&page=0&size=2
```

### Known issues
- - - -
When you start application it connects to Dropbox and gets file path and names and then stores in Solr. When you restart the application, you will lose your documents in Solr.

### Resources
- - - -
* [Generate an access token for your own account | Dropbox Developer Blog](https://blogs.dropbox.com/developers/2014/05/generate-an-access-token-for-your-own-account/)
* [Dropbox Java API Tutorial - Java Tutorial Blog](http://javapapers.com/java/dropbox-java-api-tutorial/)
* [White House API Standards](https://github.com/WhiteHouse/api-standards)
* [Getting Started with Spring Data Solr - DZone Big Data](https://dzone.com/articles/getting-started-spring-data)



