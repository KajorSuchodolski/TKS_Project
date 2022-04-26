package org.ias.tks.restadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.junit.jupiter.api.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CostumeRestTest {

    private static String path;
    private static Client restClient;

    @BeforeEach
    public void config() {
        path = "http://localhost:8080/rest-adapters-1.0-SNAPSHOT/costume";
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        restClient = clientBuilder.build();
    }

    @Test
    public void getAllTest() {

        CostumeDTO[] costumes = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(costumes[0].getName());
    }

//    @Test
//    public void getAllRentedTest() {
//        CostumeDTO[] costumes = restClient.target(path + "/all-rented")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeDTO[].class);
//
//        System.out.println(costumes);
//    }
//
//    @Test
//    public void getAllAvailableTest() {
//        CostumeDTO[] costumes = restClient.target(path + "/all-available")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeDTO[].class);
//
//        System.out.println(costumes);
//    }
//
//    @Test
//    public void getAllByAge() {
//        CostumeDTO[] costumes = restClient.target(path + "/all-by-age")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeDTO[].class);
//
//        System.out.println(Arrays.toString(costumes));
//    }
//
//    @Test
//    public void getByIdTest() {
//        CostumeDTO costume = restClient.target(path + "/id")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeDTO.class);
//
//        System.out.println(costume);
//    }
//
//    @Test
//    public void searchByNameTest() {
//        CostumeDTO[] costumes = restClient.target(path + "/search-by-name")
//                .queryParam("name","Furry")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeDTO[].class);
//
//        System.out.println(Arrays.toString(costumes));
//    }
//
//    @Test
//    public void costumesByParamsTest() {
//
//    }
//
//
//
//
//    @Test
//    public void addCostumeTest() {
//        String newCostume = "{\n" +
//                " \"costumeSize\" : S, \n" +
//                " \"name\": \"Fairy\",\n" +
//                " \"price\": 100, \n" +
//                " \"forWhom\": GIRLS\n" + "}";
//
////        Assertions.assertEquals(200, responsePost);
//    }
//
//    @Test
//    public void updateCostumeTest() {
//
//    }
//
//    @Test
//    public void deleteCostumeTest() {
//
//    }
}
