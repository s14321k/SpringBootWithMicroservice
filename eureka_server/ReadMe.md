# @EnableEurekaServer

**Add this in app prop**
- eureka.server.maxThreadsForPeerReplication=0

- # These two will stop showing the eureka application with client app in the browser
- eureka.client.registerWithEureka=false
- eureka.client.fetchRegistry=false