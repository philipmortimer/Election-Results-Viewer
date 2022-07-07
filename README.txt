App created using MariaDB, Java and react. Server creates API that delivers election results. Website is used to fetch and interact with this data. This was done as part of a University work.

To run server you will need to install vagrant and virtual box (or equivalent). To start server use "vagrant up" (with command line inside of "Vagrant" folder). "vagrant halt" will terminate the server.
To run the Java server, run "mvn spring-boot:run" in root (same level as "pom.xml" file).
Go to "localhost:8000" in a web browser to view the application.

An example of the program output is also included.