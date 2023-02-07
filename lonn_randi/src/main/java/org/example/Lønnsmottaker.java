package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lønnsmottaker {

//    @Todo: lage en arrayList med Loenn som kan lagre instanser med Loenn.
//    @Todo: Dersom denne inneholder en lik instans skal de merges, hvis ikke skal den ta inn den nye instansen i leggTil Metoden.

    private ArrayList<Loenn> alleLoenn = new ArrayList<>();
    private String id;
    private String dato;
    private String ansattNr = "";
    private double forskuddstrekk = 0;


    public Lønnsmottaker(String identifikator, String dato) {
        id = identifikator;
        this.dato = dato;
    }
    public String hentIdentifikator(){
        return id;
    }

//    @Todo: lage en instans av Loenn
//    @Todo: sjekke om den nye instansen er lik noen av de andre.
//    @Todo: hvis instansen er lik, øke instansen av den gamle med sum og antall, hvis ikke lagre den nye instansen.
    public void leggTilLoenn(Loenn nyLoenn){
        Loenn hentetLoenn = null;
//        Loenn nyLoenn = l;
        boolean fantDuplikat = false;

        for (int i=0 ; i<alleLoenn.size(); i++){
           Loenn l = alleLoenn.get(i);
            if (l.equals(nyLoenn)){
                l.merge(nyLoenn);
                fantDuplikat = true;
            }
        }
        if (!fantDuplikat){
            alleLoenn.add(nyLoenn);
        }
    }

    public void leggTilForskuddstrekk(double sum){
        forskuddstrekk += sum;
    }
    public double hentForskuddstrekk(){
        return forskuddstrekk;
    }

    @Override
    public String toString(){
        String strengen = "";

        for (int i=0; i<alleLoenn.size(); i++){
            strengen = strengen + alleLoenn.get(i).toString() + "\n";
        }

        //        String strengen = String.format("Ansatt nr, personnummer, Lønnsart nr, dato, Antall, Sats, Beskrivelse (Fordel, skatteTrekk, Aga), Total sum");
//        strengen = strengen + String.format("%s;%s;;%s;1;%s;Forskuddstrekk (Skattetrekk,false,false); %s\n",ansattNr, "'"+id, dato,  Double.toString(forskuddstrekk).replace(".",","), Double.toString(forskuddstrekk).replace(".",","));
        return strengen;
    }

    public String hentId(){
        return id;
    }
    public void settAnsattnr(String nr){
        ansattNr = nr;
    }
    public String hentAnsattNr(){
        return ansattNr;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof Lønnsmottaker){
            Lønnsmottaker l = (Lønnsmottaker) o;
            if (hentId().equals(l.hentId())){
                return true;
            }
        }
        return false;
    }

}
