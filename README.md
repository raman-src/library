## A small REST api for SMU project report

`mvn package` to build a jar file in the target directory. 

in the target directory run `java -jar library-0.0.1-SNAPSHOT` to run the application

`localhost:8080/register/v1` to get the apiKey.

`localhost:8080/books/v1` is the enpoint to access Book Resource and `localhost:8080/authors/v1` to access Author Resource. the *`apiKey`* is needed first to get access to the resources and once avaliable, must be passed in as a header with name *`apiKey`* to be able to access the reources.
