package org.ias.tks.soapadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class UserSoapIT {


    private static String context;

    private static Client restClient;
    private static PostSoapAPI postSoapAPI;

    @Container
    private static GenericContainer soapService = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payara/server-full:5.2022.2-jdk11")
                            .copy("soap-adapters-1.0-SNAPSHOT.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("soap-adapters-1.0-SNAPSHOT.war", Path.of("target", "soap-adapters-1.0-SNAPSHOT.war"))
    )
            .withExposedPorts(8080)
            .waitingFor(Wait.forHttp("/soap-adapters-1.0-SNAPSHOT/usersoapapi")
                    .forPort(8080)
                    .forStatusCode(200));


    @BeforeAll
    public static void setup() {
        context = "/usersoapapi";
        postSoapAPI = new PostSoapAPI();
    }

    @BeforeEach
    public void config() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        restClient = clientBuilder.build();
    }



    @Test
    public void getAllTest() {
        try {
            Element element = postSoapAPI.postRequest(context, "user/getAllUsers.xml", null);
            Assertions.assertEquals(3, element.getElementsByTagName("return").getLength());
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getByLoginTest() {

        try {
            Element element = postSoapAPI.postRequest(context, "user/getUserByLogin.xml", "KajorSuchodolski");
            Assertions.assertEquals("Radosław", element.getElementsByTagName("name").item(0).getTextContent());
            Assertions.assertEquals("Hyzy", element.getElementsByTagName("name").item(0).getTextContent());
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

    }
//
//    @Order(3)
//    @Test
//    public void addUserTest() {
//        UserInputDto newUser = new UserInputDto("Anna", "Broniewska", "annabron1234",
//                "annabroniewska123", "loremipsum32@gmail.com", "Manager");
//
//        Response res = restClient.target(path)
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.json(newUser));
//
//        Assertions.assertEquals(200, res.getStatus());
//
//        Response response = restClient.target(path + "/annabron1234/activate")
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.json(""));
//
//        Assertions.assertEquals(200, res.getStatus());
//    }
//
//    @Order(5)
//    @Test
//    public void searchUserByLoginTest() {
//        UserOutputDto[] foundUsers = restClient.target(path + "/search-by-login")
//                .queryParam("login","Radek")
//                .request(MediaType.APPLICATION_JSON)
//                .get(UserOutputDto[].class);
//
//        Assertions.assertEquals("Hyzy", foundUsers[0].getLastName());
//        Assertions.assertEquals("Kowalski", foundUsers[1].getLastName());
//    }
//
//    @Order(6)
//    @Test
//    public void uptadeUserTest() {
//        UserOutputDto foundUser = restClient.target(path + "/get-by-login")
//                .queryParam("login","KajorSuchodolski")
//                .request(MediaType.APPLICATION_JSON)
//                .get(UserOutputDto.class);
//
//
//        UserInputDto modifiedUser = new UserInputDto("Aleksander", "Koniczyna",
//                "KajorSuchodolski", "admin1", "radek2000@onet.pl", "Manager");
//
//        Response res = restClient.target(path + "/KajorSuchodolski/update")
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.entity(modifiedUser, MediaType.APPLICATION_JSON));
//        Assertions.assertEquals(200, res.getStatus());
//
//
//        UserOutputDto foundModifiedUser = restClient.target(path + "/get-by-login")
//                .queryParam("login","KajorSuchodolski")
//                .request(MediaType.APPLICATION_JSON)
//                .get(UserOutputDto.class);
//
//        Assertions.assertEquals("KajorSuchodolski", foundModifiedUser.getLogin());
//        Assertions.assertEquals("Aleksander", foundModifiedUser.getFirstName());
//        Assertions.assertEquals("Koniczyna", foundModifiedUser.getLastName());
//        Assertions.assertEquals("radek2000@onet.pl", foundModifiedUser.getEmail());
//        Assertions.assertEquals("Manager", foundModifiedUser.getAccessLevel());
//    }
//
//    @Order(7)
//    @Test
//    public void deactivateUserTest() {
//        Response response = restClient.target(path + "/KajorSuchodolski/deactivate")
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.json(""));
//
//        Assertions.assertEquals(200, response.getStatus());
//
//        UserOutputDto user = restClient.target(path + "/get-by-login")
//                .queryParam("login","KajorSuchodolski")
//                .request(MediaType.APPLICATION_JSON)
//                .get(UserOutputDto.class);
//
//        Assertions.assertFalse(user.isActive());
//    }
//
//    @Order(8)
//    @Test
//    public void activateUserTest() {
//        Response response = restClient.target(path + "/KajorSuchodolski/activate")
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.json(""));
//
//        Assertions.assertEquals(200, response.getStatus());
//
//        UserOutputDto user = restClient.target(path + "/get-by-login")
//                .queryParam("login","KajorSuchodolski")
//                .request(MediaType.APPLICATION_JSON)
//                .get(UserOutputDto.class);
//
//        Assertions.assertTrue(user.isActive());
//    }
}
