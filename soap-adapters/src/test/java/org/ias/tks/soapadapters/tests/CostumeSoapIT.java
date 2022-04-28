package org.ias.tks.soapadapters.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CostumeSoapIT {

    private static String path;
    private static String context;

    private static Client restClient;
    private static PostSoapAPI postSoapAPI;


    @BeforeAll
    public static void setup() {
        path = "http://localhost:8080/soap-adapters-1.0-SNAPSHOT";
        context = "/costumesoapapi";
        postSoapAPI = new PostSoapAPI();
    }

    @BeforeEach
    public void config() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        restClient = clientBuilder.build();
    }

    @Test
    public void getAllTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = postSoapAPI.postRequest(context, "costume/getAll.xml", null);
        Assertions.assertEquals(5, element.getElementsByTagName("return").getLength());
//        Assertions.assertEquals("Furry Costume", element.getElementsByTagName("name").item(0).getTextContent());
//        Assertions.assertEquals("XL", element.getElementsByTagName("costumeSize").item(0).getTextContent());
//        Assertions.assertEquals("BOYS", element.getElementsByTagName("forWhom").item(0).getTextContent());
//        Assertions.assertEquals(100, element.getElementsByTagName("price").item(0).getTextContent());
    }

    @Test
    public void getAllAvailableTest() throws IOException, ParserConfigurationException, SAXException {

        Element element = postSoapAPI.postRequest(context, "costume/getAllAvailable.xml", null);
        Assertions.assertEquals(100, element.getElementsByTagName("price").item(0).getTextContent());
        Assertions.assertEquals(66, element.getElementsByTagName("price").item(0).getTextContent());
        Assertions.assertEquals(42, element.getElementsByTagName("price").item(0).getTextContent());
        Assertions.assertEquals(10, element.getElementsByTagName("price").item(0).getTextContent());
        Assertions.assertEquals(110, element.getElementsByTagName("price").item(0).getTextContent());
    }


    @Test
    public void getByIdTest() throws IOException, ParserConfigurationException, SAXException {
        Element element = postSoapAPI.postRequest(context, "costume/getAllAvailable.xml", null);

        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String name = element.getElementsByTagName("name").item(0).getTextContent();

        element = postSoapAPI.postRequest(context, "costume/get", id);

        Assertions.assertEquals(name, element.getElementsByTagName("name").item(0).getTextContent());
    }


//    @Test
//    public void addCostumeTest() {
//        CostumeInputSOAP newCostume = new CostumeInputSOAP("Fairy", "S", "GIRLS", 120);
//        Response res = restClient.target(path)
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(newCostume, MediaType.APPLICATION_JSON));
//
//        Assertions.assertEquals(200, res.getStatus());
//    }

//    @Test
//    public void updateCostumeTest() {
//        CostumeOutputSOAP[] costumes = restClient.target(path + "/search-by-name")
//                .queryParam("name","Pope")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeOutputSOAP[].class);
//
//        UUID updatedCostumeId = UUID.fromString(costumes[0].getId());
//
//        CostumeInputSOAP newCostume = new CostumeInputSOAP("Hanyuu", "S", "GIRLS", 90);
//
//        Response res = restClient.target(path + "/" + updatedCostumeId.toString())
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.entity(newCostume, MediaType.APPLICATION_JSON));
//
//        Assertions.assertEquals(200, res.getStatus());
//
//        CostumeOutputSOAP[] newCostumes = restClient.target(path + "/search-by-name")
//                .queryParam("name","Cowiek Maupa")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeOutputSOAP[].class);
//
//        CostumeOutputSOAP updatedCostume = newCostumes[0];
//
//        Assertions.assertEquals("Cowiek Maupa", updatedCostume.getName());
//        Assertions.assertEquals("S", updatedCostume.getCostumeSize().toString());
//        Assertions.assertEquals("MAN", updatedCostume.getForWhom().toString());
//        Assertions.assertEquals(90, updatedCostume.getPrice());
//    }
//
//    @Test
//    public void deleteCostumeTest() throws InterruptedException {
//        CostumeOutputSOAP[] costume = restClient.target(path + "/search-by-name")
//                .queryParam("name","Zorro")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeOutputSOAP[].class);
//
//        Response res = restClient.target(path + "/" + costume[0].getId())
//                .request(MediaType.APPLICATION_JSON)
//                .delete();
//
//        Assertions.assertEquals(200, res.getStatus());
//
//        CostumeInputSOAP[] newCostume = restClient.target(path + "/search-by-name")
//                .queryParam("name","Zorro")
//                .request(MediaType.APPLICATION_JSON)
//                .get(CostumeInputSOAP[].class);
//
//        Assertions.assertTrue(newCostume.length == 0);
//    }

}
