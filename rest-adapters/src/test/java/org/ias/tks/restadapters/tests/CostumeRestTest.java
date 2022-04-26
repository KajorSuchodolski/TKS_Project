package org.ias.tks.restadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;
import java.util.Arrays;

import org.testcontainers.utility.MountableFile;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@Slf4j
public class CostumeRestTest {

    private static String path;
    private static Client restClient;
    private static WebTarget restTarget;

    private static MountableFile warFile = MountableFile.forHostPath(Paths.get("target/rest-adapters-1.0-SNAPSHOT.war").toAbsolutePath(), 0777);

    @Container
    public static GenericContainer restService = new GenericContainer("payara/micro:latest")
            .withExposedPorts(8080)
            .withCopyFileToContainer(warFile, "/opt/payara/deployments/rest-adapters-1.0-SNAPSHOT.war")
            .waitingFor(Wait.forLogMessage(".* Payara Micro .* ready in .*\\s", 1))
            .withCommand("--deploy /opt/payara/deployments/rest-adapters-1.0-SNAPSHOT.war")
            .withLogConsumer(new Slf4jLogConsumer(log));


    @BeforeAll
    public static void config() {
        path = "http://localhost:8080/rest-adapters-1.0-SNAPSHOT/costume"; // port do zmiany!!!
        restClient = ClientBuilder.newClient();
        restTarget = restClient.target(path);
    }

    @Test
    public void getAllTest() {
        System.out.println(restService.getExposedPorts());
        System.out.println(restService.getHost());
        while(true) {

        }
//        CostumeDTO[] costumes = restClient.target(path)
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeDTO[].class);

//        System.out.println(costumes);
    }

    @Test
    public void getAllRentedTest() {
        CostumeDTO[] costumes = restClient.target(path + "/all-rented")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(costumes);
    }

    @Test
    public void getAllAvailableTest() {
        CostumeDTO[] costumes = restClient.target(path + "/all-available")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(costumes);
    }

    @Test
    public void getAllByAge() {
        CostumeDTO[] costumes = restClient.target(path + "/all-by-age")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(Arrays.toString(costumes));
    }

    @Test
    public void getByIdTest() {
        CostumeDTO costume = restClient.target(path + "/id")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO.class);

        System.out.println(costume);
    }

    @Test
    public void searchByNameTest() {
        CostumeDTO[] costumes = restClient.target(path + "/search-by-name")
                .queryParam("name","Furry")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(Arrays.toString(costumes));
    }

    @Test
    public void costumesByParamsTest() {

    }




    @Test
    public void addCostumeTest() {
        String newCostume = "{\n" +
                " \"costumeSize\" : S, \n" +
                " \"name\": \"Fairy\",\n" +
                " \"price\": 100, \n" +
                " \"forWhom\": GIRLS\n" + "}";

//        Assertions.assertEquals(200, responsePost);
    }

    @Test
    public void updateCostumeTest() {

    }

    @Test
    public void deleteCostumeTest() {

    }
}
