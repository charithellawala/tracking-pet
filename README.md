***************** with docker configuration ******************

1.) make sure docker is up in your machine.
2.) Go to the project folder and simply run "docker-compose up --build"
3.) you can check on the api in swagger by "http://localhost:8080/swagger-ui/index.html"
4.) check the h2 database by "http://localhost:8080/h2-console" and enter,
            - url: jdbc:h2:mem:pettracker
            - username: sa without password

***************** without docker configuration ****************

1.) open project from eclipse
2.) right click on project. select Run As -> Maven Build.. -> set the goal as "clean install" and click apply and run.
3.) Go to the main class and right click on the class. Select Run As -> Java application or springbootApplication.
3.) you can check on the api in swagger by "http://localhost:8080/swagger-ui/index.html"
4.) check the h2 database by "http://localhost:8080/h2-console" and enter,
            - url: jdbc:h2:mem:pettracker
            - username: sa without password
