package de.codepitbull.hystrix.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://127.0.0.1:8085/hystrix-springmvc/spring")

  val scn = scenario("BasicSimulation")
    .exec(http("request_1")
    .get("/"))

  setUp(
    scn.inject(rampUsers(50) over (10 seconds)).throttle(reachRps(10) in (5 seconds), holdFor(2 minutes))
  ).protocols(httpConf)
}
