package org.ias.tks.restadapters.tests;

import org.ias.tks.restadapters.dto.user.UserOutputDto;
import org.junit.jupiter.api.*;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class UserRestTest {

    private static String path;
    private static Client restClient;

    @BeforeEach
    public void config() {
        path = "http://localhost:8080/rest-adapters-1.0-SNAPSHOT/user";
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        restClient = clientBuilder.build();
    }

    @Test
    public void getAllTest() {
        UserOutputDto[] users = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto[].class);

        Assertions.assertEquals("Radoslaw", users[0].getFirstName());
        Assertions.assertEquals("Zyzik", users[0].getLastName());
        Assertions.assertEquals("radek2000@onet.pl", users[0].getEmail());
        Assertions.assertEquals("KajorSuchodolski", users[0].getLogin());
        Assertions.assertEquals("MANAGER", users[0].getAccessLevel().toString());

    }

    @Test
    public void getByLoginTest() {
        UserOutputDto foundUser = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertEquals("radek2000@onet.pl", foundUser.getEmail());
    }

//    @Test
//    public void addUserTest() {
//        String newUser = "{\n" +
//                " \"firstName\" : \"Zbigniew\", \n" +
//                " \"lastName\": \"Kowalski\",\n" +
//                " \"login\": \"kowalski456\", \n" +
//                " \"password\": \"kowal123\" \n" + "}";
//
//        Response responsePost = restClient.target(path)
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.json(newUser));
//
//        Assertions.assertEquals(200, responsePost);
//    }
//
//    @Test
//    public void getUserByIdTest() {
//        UserOutputDto foundUser = restClient.target(path + "/get-by-id")
////                .queryParam("id", "KajorSuchodolski")
//                .request(MediaType.APPLICATION_JSON)
//                .get(UserOutputDto.class);
//        System.out.println(foundUser);
//    }
//
    @Test
    public void searchUserByLoginTest() {
        UserOutputDto[] foundUsers = restClient.target(path + "/search-by-login")
                .queryParam("login","Radek")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto[].class);

//        Assertions.assertEquals();
        System.out.println(Arrays.toString(foundUsers));
    }
//
////    @Order(6)
////    @Test
////    public void uptadeUserTest() {
////        String newUser = "{\n" +
////                " \"firstName\" : \"Zbigniew\", \n" +
////                " \"lastName\": \"Kowalski\",\n" +
////                " \"login\": \"kowalski456\", \n" +
////                " \"password\": \"kowal123\" \n" + "}";
//
////        Response response = restClient.target(path + "/KajorSuchodolski/update")
////                .request(MediaType.APPLICATION_JSON)
////                .put();
////    }
//
//    @Test
//    public void activateUserTest() {
//        Response response = restClient.target(path + "/KajorSuchodolski/activate")
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.json(""));
//
//        Assertions.assertEquals(200, response);
//    }
//
    @Test
    public void deactivateUserTest() {
        Response response = restClient.target(path + "/KajorSuchodolski/deactivate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        Assertions.assertEquals(200, response);

        UserOutputDto user = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertEquals(false, user.isActive());
    }

}

