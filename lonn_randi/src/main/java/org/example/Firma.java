package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Firma {
    HashMap<String,Lønnsmottaker> lMottakere = new HashMap<>();
    String navn;
    String orgnr;
    HashMap<String, Double> totaler = new HashMap<>();
    Control kontroll;
    public Firma(Control kontroll){
        this.kontroll = kontroll;
    }
    public void settOrgNr(String nr){
        orgnr = nr;
    }
    public void settNavn(String navn){
        this.navn = navn;
    }

    public void leggTilMottaker(Lønnsmottaker m){
        lMottakere.put(m.hentIdentifikator(), m);
    }
    public HashMap<String,Lønnsmottaker> hentAlleMottakere(){
        return lMottakere;
    }
    public Lønnsmottaker hentMottaker(String id){
        return lMottakere.get(id);
    }

//    @Todo: må oppdatere slik at denne benytter lønnsmottaker sin legg tiul metode.

    public void lesFraNodeList(NodeList alleMottakere, String dato) {
        for (int i = 0; i < alleMottakere.getLength(); i++) {
            Element mottaker = (Element) alleMottakere.item(i);
            String id = mottaker.getElementsByTagName("norskIdentifikator").item(0).getTextContent();
            NodeList inntekter = mottaker.getElementsByTagName("inntekt");
            Lønnsmottaker loennsmottaker = new Lønnsmottaker(id, dato);
            lMottakere.put(id, loennsmottaker);
            for (int j = 0; j < inntekter.getLength(); j++) {
//                @Todo: Må hente ut antall for hver lønn og oppdatere det. Husk at antall kan være null.
                Element inntekt = (Element) inntekter.item(j);
                String fordel = inntekt.getElementsByTagName("fordel").item(0).getTextContent();
                double beloep = Double.parseDouble(inntekt.getElementsByTagName("beloep").item(0).getTextContent());

                String beskrivelse = inntekt.getElementsByTagName("beskrivelse").item(0).getTextContent();
                boolean utAga = Boolean.parseBoolean(inntekt.getElementsByTagName("utloeserArbeidsgiveravgift").item(0).getTextContent());
                boolean trekk = Boolean.parseBoolean(inntekt.getElementsByTagName("inngaarIGrunnlagForTrekk").item(0).getTextContent());

                Loenn loenn = new Loenn(beskrivelse, fordel, trekk, utAga, dato, loennsmottaker);
                loenn.okSum(beloep);
                try{
                    double antall = Double.parseDouble(inntekt.getElementsByTagName("antall").item(0).getTextContent());
                    loenn.okAntall(antall);
                }catch(NullPointerException e){
                }
                loennsmottaker.leggTilLoenn(loenn);
            }
            Element elem = (Element) mottaker.getElementsByTagName("forskuddstrekk").item(0);
            if (elem == null){
            }else{
                Node beloepNode =elem.getElementsByTagName("beloep").item(0);
                double beloep = Double.parseDouble(beloepNode.getTextContent());
                loennsmottaker.leggTilForskuddstrekk(beloep);

            }

            lMottakere.put(id, loennsmottaker);

        }
        //System.out.println(this);
    }
    public void settOverordnet(Element o) {
        NodeList alleInntekt = o.getElementsByTagName("inntekt");
//        System.out.println("er inne i denne 1");
        for (int i=0; i<alleInntekt.getLength();i++){
//            System.out.println("er inne i denne 2");
            Element inntekt = (Element) alleInntekt.item(i);
            if (inntekt.getParentNode() == o){
//                System.out.println("er inne i denne 3");
                String type = inntekt.getElementsByTagName("beskrivelse").item(0).getTextContent();
                double sum = Double.parseDouble(inntekt.getElementsByTagName("beloep").item(0).getTextContent());
                totaler.put(type, sum);
            }
        }

    }
    public String skrivTilFil(File fil){
        String strengen = "data er skrevet til fil";
        try{
            FileWriter writer = new FileWriter(fil);
            writer.write(this.toString());
            writer.close();
            kontroll.settDialog("Skrevet til fil");
        }catch (IOException e){
            return "Error, could not write to file";
        }
        return strengen;
    }
    @Override
    public String toString(){
//        String strengen = String.format("%s, %s\n", navn, orgnr);

        String strengen = String.format("Ansatt nr; personnummer; Lønnsart nr; dato; Antall; Sats; Beskrivelse (Fordel, skatteTrekk, Aga);Total\n");
        strengen = strengen + String.format("Ansatt nr; ; Lønnart nr; Dato; antall; Sats; Beskrivelse; ; Prosjektnr; avdelingsnr\n") ;

        Map<String, Lønnsmottaker> map = new HashMap<>(lMottakere);
        for (Map.Entry<String, Lønnsmottaker> set: map.entrySet()){
            Lønnsmottaker m = set.getValue();
            strengen = strengen + m.toString();
        }
        return strengen;
    }
    public Set<String> hentID(){
        System.out.println(lMottakere);
        return lMottakere.keySet();
    }
    public void settAnsattNr(String id, String nr){
        Lønnsmottaker mottaker = lMottakere.get(id);
        mottaker.settAnsattnr(nr);
    }
}
