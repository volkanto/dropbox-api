# Dropbox API - Extended
### Task
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

### How to run
`git clone https://github.com/volkanto/dropbox-api-extended`


### How to use

### Resources
* [Dropbox Java API Tutorial - Java Tutorial Blog](http://javapapers.com/java/dropbox-java-api-tutorial/)
* [White House API Standards](https://github.com/WhiteHouse/api-standards)
* [Solr in 5 minutes](http://www.solrtutorial.com/solr-in-5-minutes.html)
