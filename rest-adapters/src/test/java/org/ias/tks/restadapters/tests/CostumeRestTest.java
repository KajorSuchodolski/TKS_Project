package org.ias.tks.restadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
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

    @Test
    public void getAllByAge() {
        CostumeDTO[] costumesMan = restClient.target(path + "/all-by-age")
                .queryParam("age", ForWhom.MAN)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals("Pope", costumesMan[0].getName());
    }

    @Test
    public void getByIdTest() {

        CostumeDTO costume = restClient.target(path + "/" )
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

        Assertions.assertEquals(100, costumes[0].getPrice());
        Assertions.assertEquals(66, costumes[1].getPrice());
    }
//
//    @Test
//    public void costumesByParamsTest() {
//
//    }
//
//
//
//
    @Test
    public void addCostumeTest() {
        CostumeDTO newCostume = new CostumeDTO("Fairy", CostumeSize.S, ForWhom.GIRLS, 120);
        Response res = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newCostume, MediaType.APPLICATION_JSON));

        Assertions.assertEquals(200, res.getStatus());
    }
//
    @Test
    public void updateCostumeTest() {
        CostumeDTO[] costumes = restClient.target(path + "/search-by-name")
                .queryParam("name","Furry")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        UUID updatedCostumeId = costumes[0].getId();

        CostumeDTO newCostume = new CostumeDTO("Cowiek Maupa", CostumeSize.M, ForWhom.MAN, 100);
        Response res = restClient.target(path + "/" + updatedCostumeId.toString())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(newCostume, MediaType.APPLICATION_JSON));

        Assertions.assertEquals(200, res.getStatus());

        costumes = restClient.target(path + "/search-by-name")
                .queryParam("name","Cowiek Maupa")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        CostumeDTO updatedCostume = costumes[0];

        Assertions.assertEquals("Cowiek Maupa", updatedCostume.getName());
        Assertions.assertEquals(CostumeSize.M, updatedCostume.getCostumeSize());
        Assertions.assertEquals(ForWhom.MAN, updatedCostume.getForWhom());
        Assertions.assertEquals(100, updatedCostume.getPrice());


    }

    @Test
    public void deleteCostumeTest() throws InterruptedException {

        CostumeDTO[] costume = restClient.target(path + "/search-by-name")
                .queryParam("name","Amogus Red Impostor")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);
        UUID id = costume[0].getId();

        Response res = restClient.target(path + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        System.out.println(res.getStatus());
        Assertions.assertEquals(200, res.getStatus());

        CostumeDTO[] newCostume = restClient.target(path + "/search-by-name")
                .queryParam("name","Amogus Red Impostor")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(newCostume);
        Assertions.assertNull(newCostume);

    }
}
