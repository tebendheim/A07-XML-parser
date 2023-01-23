package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Control {
    GUI gui;
    public Control(GUI gui){
        this.gui = gui;
    }
    public void kjorFirma(File orginalFil, File nyFil){
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
            Firma firma = new Firma(firmanavn, orgnr, this);
            firma.settOverordnet(oppgave);
            firma.lesFraNodeList(alleMottakere);
            NodeList alleInntekt = oppgave.getElementsByTagName("inntekt");

//            gui.showPrint(firma.toString());
         System.out.println(firma);

         firma.skrivTilFil(nyFil);
//
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void skrivTilFil(File fil, String streng){
        String strengen = "data er skrevet til fil";
        try{
            FileWriter writer = new FileWriter(fil);
            writer.write(strengen);
            settDialog("Innehold er skrevet til ny fil.");
            writer.close();
        }catch (IOException e){
            settDialog("Exception: "+e.toString());
        }
    }
    public void settDialog(String dialog){
        gui.setDialog(dialog);
    }
}
