Clone the Hystrix [Git-Repo](https://github.com/Netflix/Hystrix), go to __hystrix-dashboard__ inside and run 
__./gradlew jettyRun__. The log output contains the url to reach the dashboard.
  
Now to a __mvn clean tomcat7:run -Dmaven.tomcat.port=8085__ to get this application up and running. 

#Application URLs#
[The main application](http://127.0.0.1:8085/hystrix-springmvc/spring/)

[The turbine stream](http://127.0.0.1:8085/hystrix-springmvc/turbine.stream?cluster=default)

Profit!!