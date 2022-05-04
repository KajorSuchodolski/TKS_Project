package org.ias.tks.restadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.appports.repoadapters.repositories.RentRepository;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.junit.jupiter.api.*;

import javax.ws.rs.client.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CostumeRestIT {

    private static String path;
    private static Client restClient;
    private RentRepository rentRepo;

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

        System.out.println(costumes[0].getName());
        System.out.println(costumes.length);

        Assertions.assertEquals(5, costumes.length);
        Assertions.assertNotNull(costumes);
    }

    @Order(2)
    @Test
    public void getAllAvailableTest() {
        CostumeDTO[] costumes = restClient.target(path + "/all-available")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);


        Assertions.assertEquals(5, costumes.length);
        Assertions.assertNotNull(costumes);
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

        Assertions.assertEquals("Furry Costume", costumes[0].getName());
        Assertions.assertEquals("Furry Costume", costumes[1].getName());
    }

    @Order(5)
    @Test
    public void costumesByParamsTest() {
        CostumeDTO[] costumes = restClient.target(path + "/costumes-by-params")
                .queryParam("age","GIRLS")
                .queryParam("size","XL")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        Assertions.assertEquals(ForWhom.GIRLS, costumes[0].getForWhom());
        Assertions.assertEquals(ForWhom.GIRLS, costumes[0].getForWhom());
        Assertions.assertEquals(CostumeSize.XL, costumes[1].getCostumeSize());
        Assertions.assertEquals(CostumeSize.XL, costumes[1].getCostumeSize());
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
                .queryParam("name","Amogus Red Impostor")
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        CostumeDTO updatedCostume = newCostumes[0];

        Assertions.assertEquals("Amogus Red Impostor", updatedCostume.getName());
        Assertions.assertEquals("S", updatedCostume.getCostumeSize().toString());
        Assertions.assertEquals("BOYS", updatedCostume.getForWhom().toString());
        Assertions.assertEquals(10, updatedCostume.getPrice());
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