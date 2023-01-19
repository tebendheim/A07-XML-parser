package org.example;

import org.w3c.dom.Element;

public class Arbeidsgiveravgift {
    Element element;
    double loennOgGodtgjoerelse = 0;
    double tilskuddOgPremieTilPensjon = 0;
    double totAga = 0;
    public Arbeidsgiveravgift(Element el){
        element = el;
        regnut();
    }
    public void regnut(){
        Element lOgG = (Element) element.getElementsByTagName("loennOgGodtgjoerelse");
        Element tOgP = (Element) element.getElementsByTagName("tilskuddOgPremieTilPensjon");
        loennOgGodtgjoerelse = Double.parseDouble(lOgG.getElementsByTagName("avgiftsgrunnlagBeloep").item(0).getTextContent());
        tilskuddOgPremieTilPensjon = Double.parseDouble(tOgP.getElementsByTagName("avgiftsgrunnlagBeloep").item(0).getTextContent());
        totAga = loennOgGodtgjoerelse+tilskuddOgPremieTilPensjon;
    }
    public double hentAga(){
        return totAga;
    }
    public double hentAgaLoennOgGodtgjoerelse(){
        return loennOgGodtgjoerelse;
    }
    public double hentAgaTilskuddOgPremieTilPensjon(){
        return tilskuddOgPremieTilPensjon;
    }

}
