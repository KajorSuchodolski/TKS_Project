package org.ias.tks.restadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.appports.repoadapters.entities.costume.ForWhomEnt;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.junit.jupiter.api.*;

import javax.ws.rs.client.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.UUID;


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

    @Order(1)
    @Test
    public void getAllTest() {
        CostumeDTO[] costumes = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals("Furry Costume", costumes[0].getName());
        Assertions.assertEquals("XL", costumes[0].getCostumeSize().toString());
        Assertions.assertEquals("BOYS", costumes[0].getForWhom().toString());
        Assertions.assertEquals(100, costumes[0].getPrice());
    }

    @Order(2)
    @Test
    public void getAllAvailableTest() {
        CostumeDTO[] costumes = restClient.target(path + "/all-available")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals(100, costumes[0].getPrice());
        Assertions.assertEquals(66, costumes[1].getPrice());
        Assertions.assertEquals(42, costumes[2].getPrice());
        Assertions.assertEquals(10, costumes[3].getPrice());
        Assertions.assertEquals(110, costumes[4].getPrice());
    }

    @Order(3)
    @Test
    public void getAllByAge() {
        CostumeDTO[] costumesMan = restClient.target(path + "/all-by-age")
                .queryParam("age", "MAN")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals("Pope", costumesMan[0].getName());
    }

    @Order(3)
    @Test
    public void getByIdTest() {
        CostumeDTO[] costumes = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);
        UUID id = costumes[0].getId();

        CostumeDTO costume = restClient.target(path + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO.class);

        Assertions.assertEquals(costume.getId(), id);
    }

    @Order(4)
    @Test
    public void searchByNameTest() {
        CostumeDTO[] costumes = restClient.target(path + "/search-by-name")
                .queryParam("name","Furry")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals(100, costumes[0].getPrice());
        Assertions.assertEquals(66, costumes[1].getPrice());
    }

    @Order(5)
    @Test
    public void costumesByParamsTest() {
        CostumeDTO[] costumes = restClient.target(path + "/costumes-by-params")
                .queryParam("age","GIRLS")
                .queryParam("size","XL")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals(66, costumes[0].getPrice());
        Assertions.assertEquals(110, costumes[1].getPrice());
    }

    @Order(6)
    @Test
    public void addCostumeTest() {
        CostumeDTO newCostume = new CostumeDTO("Fairy", CostumeSize.S, ForWhom.GIRLS, 120);
        Response res = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newCostume, MediaType.APPLICATION_JSON));

        Assertions.assertEquals(200, res.getStatus());
    }

    @Order(7)
    @Test
    public void updateCostumeTest() {
        CostumeDTO[] costumes = restClient.target(path + "/search-by-name")
                .queryParam("name","Pope")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        UUID updatedCostumeId = costumes[0].getId();

        CostumeDTO newCostume = new CostumeDTO("Hanyuu", CostumeSize.S, ForWhom.GIRLS, 90);

        Response res = restClient.target(path + "/" + updatedCostumeId.toString())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(newCostume, MediaType.APPLICATION_JSON));

        Assertions.assertEquals(200, res.getStatus());

        CostumeDTO[] newCostumes = restClient.target(path + "/search-by-name")
                .queryParam("name","Hanyuu")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        CostumeDTO updatedCostume = newCostumes[0];

        Assertions.assertEquals("Hanyuu", updatedCostume.getName());
        Assertions.assertEquals("S", updatedCostume.getCostumeSize().toString());
        Assertions.assertEquals("GIRLS", updatedCostume.getForWhom().toString());
        Assertions.assertEquals(90, updatedCostume.getPrice());
    }

    @Order(8)
    @Test
    public void deleteCostumeTest() throws InterruptedException {
        CostumeDTO[] costume = restClient.target(path + "/search-by-name")
                .queryParam("name","Zorro")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Response res = restClient.target(path + "/" + costume[0].getId())
                .request(MediaType.APPLICATION_JSON)
                .delete();

        Assertions.assertEquals(200, res.getStatus());

        CostumeDTO[] newCostume = restClient.target(path + "/search-by-name")
                .queryParam("name","Zorro")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertTrue(newCostume.length == 0);
    }
}
