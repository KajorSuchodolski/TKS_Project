package org.ias.tks.restadapters.tests;

import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.ias.tks.restadapters.dto.user.UserInputDto;
import org.ias.tks.restadapters.dto.user.UserOutputDto;
import org.junit.jupiter.api.*;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.UUID;

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
        Assertions.assertEquals("Manager", users[0].getAccessLevel().toString());
    }

    @Test
    public void getByLoginTest() {
        UserOutputDto foundUser = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertEquals("radek2000@onet.pl", foundUser.getEmail());
    }

    @Test
    public void addUserTest() {
        UserInputDto newUser = new UserInputDto("Anna", "Broniewska", "annabron1234",
                "annabroniewska123", "loremipsum32@gmail.com", "Manager");

        Response res = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newUser));

        Assertions.assertEquals(200, res.getStatus());

        Response response = restClient.target(path + "/annabron1234/activate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        Assertions.assertEquals(200, res.getStatus());
    }

    @Test
    public void getUserByIdTest() {
        UserOutputDto foundUser = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        UserOutputDto foundUserById = restClient.target(path + "/get-by-id")
                .queryParam("id", foundUser.getId())
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertEquals(foundUser.getEmail(), foundUserById.getEmail());
    }

    @Test
    public void searchUserByLoginTest() {
        UserOutputDto[] foundUsers = restClient.target(path + "/search-by-login")
                .queryParam("login","Radek")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto[].class);

        Assertions.assertEquals("Hyzy", foundUsers[0].getLastName());
        Assertions.assertEquals("Kowalski", foundUsers[1].getLastName());
    }

    @Test
    public void uptadeUserTest() {
        UserOutputDto foundUser = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);


        UserInputDto modifiedUser = new UserInputDto("Aleksander", "Koniczyna",
                "KajorSuchodolski", "admin1", "radek2000@onet.pl", "Manager");

        Response res = restClient.target(path + "/KajorSuchodolski/update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(modifiedUser, MediaType.APPLICATION_JSON));
        Assertions.assertEquals(200, res.getStatus());


        UserOutputDto foundModifiedUser = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertEquals("KajorSuchodolski", foundModifiedUser.getLogin());
        Assertions.assertEquals("Aleksander", foundModifiedUser.getFirstName());
        Assertions.assertEquals("Koniczyna", foundModifiedUser.getLastName());
        Assertions.assertEquals("radek2000@onet.pl", foundModifiedUser.getEmail());
        Assertions.assertEquals("Manager", foundModifiedUser.getAccessLevel());
    }

    @Test
    public void deactivateUserTest() {
        Response response = restClient.target(path + "/KajorSuchodolski/deactivate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        Assertions.assertEquals(200, response.getStatus());

        UserOutputDto user = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertFalse(user.isActive());
    }

    @Test
    public void activateUserTest() {
        Response response = restClient.target(path + "/KajorSuchodolski/activate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        Assertions.assertEquals(200, response.getStatus());

        UserOutputDto user = restClient.target(path + "/get-by-login")
                .queryParam("login","KajorSuchodolski")
                .request(MediaType.APPLICATION_JSON)
                .get(UserOutputDto.class);

        Assertions.assertTrue(user.isActive());
    }

}

