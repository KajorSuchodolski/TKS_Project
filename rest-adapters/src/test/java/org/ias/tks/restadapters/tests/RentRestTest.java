//package org.ias.tks.restadapters.tests;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.output.Slf4jLogConsumer;
//import org.testcontainers.containers.wait.strategy.Wait;
//import org.testcontainers.images.builder.ImageFromDockerfile;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import java.nio.file.Path;
//import lombok.extern.slf4j.Slf4j;
//
//
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Testcontainers
//@Slf4j
//public class RentRestTest {
//
//    private static String path;
//    private static Client restClient;
//    private static WebTarget restTarget;
//
//    @BeforeAll
//    public static void config() {
//        path = "http://localhost:" + restService.getMappedPort(8080) + "/rent";
//        restClient = ClientBuilder.newClient();
//        restTarget = restClient.target(path);
//    }
//
//
//}
