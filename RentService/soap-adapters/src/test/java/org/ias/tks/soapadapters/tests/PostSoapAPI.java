package org.ias.tks.soapadapters.tests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PostSoapAPI {

    private static final String path = "http://localhost:8080/soap-adapters-1.0-SNAPSHOT";

    public Element postRequest(String context, String xmlFile, String arg0) throws IOException, SAXException, ParserConfigurationException {

        String requestContent = new Scanner(new File(getClass().getClassLoader().getResource(xmlFile).getFile())).useDelimiter("\\Z").next();

        if (arg0 != null) requestContent.replaceAll("simpleArg0", arg0);


        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(path + context);
        StringEntity stringEntity = new StringEntity(requestContent);
        post.setEntity(stringEntity);
        post.addHeader("Content-Type", "text/xml");
        HttpResponse response = client.execute(post);
        String responseXML = EntityUtils.toString(response.getEntity());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(responseXML.getBytes("UTF-8"));
        Document document = builder.parse(input);

        return document.getDocumentElement();

    }
}
