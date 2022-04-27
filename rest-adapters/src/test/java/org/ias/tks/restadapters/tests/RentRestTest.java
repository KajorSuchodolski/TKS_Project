package org.ias.tks.restadapters.tests;

import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.junit.jupiter.api.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class RentRestTest {

    private static String path;
    private static Client restClient;

    @BeforeEach
    public void config() {
        path = "http://localhost:8080/rest-adapters-1.0-SNAPSHOT/rent";
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        restClient = clientBuilder.build();
    }

    @Test
    public void getAllTest() {

        CostumeDTO[] costumes = restClient.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO[].class);

        System.out.println(costumes[0].getName());
        //renty nie dzialaja, tylo
    }

}
