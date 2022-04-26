package org.ias.tks.restadapters.tests;

import org.ias.tks.restadapters.dto.user.UserOutputDto;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Arrays;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@Slf4j
public class UserRestTest {

    private static String path;
    private static Client restClient;
    private static WebTarget restTarget;


    @Container
    public static GenericContainer restService = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder.from("payara/server-full")
                                        .copy("rest-adapters-1.0-SNAPSHOT.war", "/opt/payara/deployments")
                                        .build())
                    .withFileFromPath("rest-adapters", Path.of("target", "rest-adapters-1.0-SNAPSHOT.war"))
            )
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/rest-adapters-1.0-SNAPSHOT").forPort(8080).forStatusCode(200))
            .withLogConsumer(new Slf4jLogConsumer(log));

    @BeforeAll
    public static void config() {
        path = "http://localhost:" + restService.getMappedPort(8080) + "/rest-adapters-1.0-SNAPSHOT/user";
        System.out.println(path);
        restClient = ClientBuilder.newClient();
        restTarget = restClient.target(path);
    }

    @Test
    public void getAllTest() {
        UserOutputDto[] users = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto[].class);

        System.out.println(users);

    }

    @Test
    public void getByLoginTest() {
        UserOutputDto foundUser = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        System.out.println(foundUser);
    }

    @Test
    public void addUserTest() {
        String newUser = "{\n" +
                " \"firstName\" : \"Zbigniew\", \n" +
                " \"lastName\": \"Kowalski\",\n" +
                " \"login\": \"kowalski456\", \n" +
                " \"password\": \"kowal123\" \n" + "}";

        Response responsePost = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newUser));

        Assertions.assertEquals(200, responsePost);
    }

    @Test
    public void getUserByIdTest() {
        UserOutputDto foundUser = restClient.target(path + "/get-by-id")
//                .queryParam("id", "KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);
        System.out.println(foundUser);
    }

    @Test
    public void searchUserByLoginTest() {
        UserOutputDto[] foundUsers = restClient.target(path + "/search-by-login")
                .queryParam("login","Radek")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto[].class);

        System.out.println(Arrays.toString(foundUsers));
    }

//    @Order(6)
//    @Test
//    public void uptadeUserTest() {
//        String newUser = "{\n" +
//                " \"firstName\" : \"Zbigniew\", \n" +
//                " \"lastName\": \"Kowalski\",\n" +
//                " \"login\": \"kowalski456\", \n" +
//                " \"password\": \"kowal123\" \n" + "}";

//        Response response = restClient.target(path + "/KajorSuchodolski/update")
//                .request(MediaType.APPLICATION_JSON)
//                .put();
//    }

    @Test
    public void activateUserTest() {
        Response response = restClient.target(path + "/KajorSuchodolski/activate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        Assertions.assertEquals(200, response);
    }

    @Test
    public void deactivateUserTest() {
        Response response = restClient.target(path + "/KajorSuchodolski/deactivate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        Assertions.assertEquals(200, response);
    }

}

