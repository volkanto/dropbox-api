# Dropbox API - Extended

### Requirements
- - - -
Docker and docker compose in your local or virtual machine. And also Solr instance. 
> You don’t need a Solr instance any more, it is defined in docker compose file.  

If you are using macOS, you can download from this link [Get started with Docker for Mac - Docker](https://docs.docker.com/docker-for-mac/) .

You can follow instructions for Ubuntu 16.04 from this link [How To Install Docker Compose on Ubuntu 16.04 | DigitalOcean](https://www.digitalocean.com/community/tutorials/how-to-install-docker-compose-on-ubuntu-16-04).

### Solr configuration
- - - -
You need to create `schema.xml` under `<solr-home>/server/solr/dropbox-core/conf` folder. And you have to state field definitions like this:
```
<field name="name" type="text_general" indexed="true" stored="true" />
<field name="path" type="text_general" indexed="true" stored="true"/>
```

> Now, you can omit this part. This part is included in docker compose configuration.  

### How to run
- - - -
First, clone related repo from Github with this command on your terminal: `git clone https://github.com/volkanto/dropbox-api.git`

Go to `dropbox-api/dropbox-api-docker/dropbox-api/content/config` folder and change `application.properties` file with your settings.

You need to change lines below:
`dropbox.api.access-token=<your-dropbox-access-token>` -> with your access token

To run docker containers; go to `dropbox-api-docker` directory and run these commands consecutively:

```
docker-compose build
docker-compose up
```

After successfully run your containers, you can reach your Solr instance with `http://localhost/solr`

You can follow next step to check API methods.

### How to use
> Create tags  
```
curl -i -H "Content-Type: application/json" -X POST -d '{"documentName":"holiday", "documentPath":"/books/holiday.txt", "descriptions":["miami","beach"]}' http://localhost/api/tags
```

> Delete tags  
```
curl -i -H "Content-Type: application/json" -X DELETE -d '{"id":"4f415794-b0ac-45a4-91ac-16669b51dac9","tags":["microservices", "nodejs"]}' http://localhost/api/tags
```

> Update tags  
```
curl -i -H "Content-Type: application/json" -X PUT -d '{"id":"4f415794-b0ac-45a4-91ac-16669b51dac9","tags":["microservices", "nodejs"]}' http://localhost/api/tags'
```

> Search tags  
```
curl -i -H "Content-Type: application/json" -X GET http://localhost/api/tags?tags=miami,beach&page=0&size=2
```

### Known issues
- - - -
When you start application it connects to Dropbox and gets file path and names and then stores in Solr. When you restart the application, you will lose your documents in Solr.

### Notes
- - - -
* If you connect another Solr instance instead of docker container which is configured in this tutorial. You have to add related `schema.xml` to your Solr server configuration folder.
* Change Solr url in `application.properties` file.
* Remove Solr related lines from `docker-compose.yml` .

### Resources
- - - -
* [Generate an access token for your own account | Dropbox Developer Blog](https://blogs.dropbox.com/developers/2014/05/generate-an-access-token-for-your-own-account/)
* [Dropbox Java API Tutorial - Java Tutorial Blog](http://javapapers.com/java/dropbox-java-api-tutorial/)
* [White House API Standards](https://github.com/WhiteHouse/api-standards)
* [Getting Started with Spring Data Solr - DZone Big Data](https://dzone.com/articles/getting-started-spring-data)
