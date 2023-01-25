package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class Control {
    private GUI gui;
    private Firma firma = new Firma(this);

    public Control(GUI gui){
        this.gui = gui;
    }
    public void kjorFirma(File orginalFil, File nyFil, String dato){
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
            firma.settOrgNr(orgnr);
            firma.settNavn(firmanavn);
            //firma = new Firma(firmanavn, orgnr, this);
            firma.settOverordnet(oppgave);
            firma.lesFraNodeList(alleMottakere, dato);
            NodeList alleInntekt = oppgave.getElementsByTagName("inntekt");

//            gui.showPrint(firma.toString());

         //firma.skrivTilFil(nyFil);
//
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void skrivTilFil(File fil){
        firma.skrivTilFil(fil);

    }
    public void settDialog(String dialog){
        gui.setDialog(dialog);
    }
    public Set<String> hentPersonnummer(){
        return firma.hentID();
    }
    public void settAnsattNr(String id, String nr){
        firma.settAnsattNr(id, nr);
    }
}
