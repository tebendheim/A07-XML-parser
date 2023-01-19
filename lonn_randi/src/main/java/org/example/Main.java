package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       try {
         DocumentBuilder builder = factory.newDocumentBuilder();

         Document doc = builder.parse(new File("/Users/tomel/Downloads/avstemmingsinfo (1).xml/"));
         doc.getDocumentElement().normalize();
         NodeList oppgaver = doc.getElementsByTagName("oppgave");
         Element oppgave = (Element) oppgaver.item(0);
         String orgnr = oppgave.getElementsByTagName("norskIdentifikator").item(0).getTextContent();
         Element aga = (Element) oppgave.getElementsByTagName("arbeidsgiveravgift").item(0);
         String firmanavn = oppgave.getElementsByTagName("navn").item(0).getTextContent();
         NodeList alleMottakere = oppgave.getElementsByTagName("inntektsmottaker");
         Firma firma = new Firma(firmanavn, orgnr);
         firma.settOverordnet(oppgave);
         firma.lesFraNodeList(alleMottakere);
         NodeList alleInntekt = oppgave.getElementsByTagName("inntekt");

//         System.out.println(firma);
//
        } catch (Exception e) {
          System.out.println(e);
       }
    }
}