package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Control {
    GUI gui;
    public Control(GUI gui){

    }
    public String kjorFirma(File orginalFil, File nyFil){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String utskrift = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(orginalFil);

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

//            gui.showPrint(firma.toString());
         System.out.println(firma);
         utskrift =  firma.skrivTilFil(nyFil);
//
        } catch (Exception e) {
            System.out.println(e);
        }
        return utskrift;
    }
}
