package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lønnsmottaker {

//    @Todo: lage en arrayList med Loenn som kan lagre instanser med Loenn.
//    @Todo: Dersom denne inneholder en lik instans skal de merges, hvis ikke skal den ta inn den nye instansen i leggTil Metoden.

    private ArrayList<Loenn> alleLoenn = new ArrayList<>();
    private String id;
//    private HashMap<String, Double> beskrivelse = new HashMap<>();
//    private HashMap<String, Double> fordel = new HashMap<>();
//    private double utloeserAga = 0;
//    private double inngaariGrunnlagForTrekk = 0;
    private double forskuddstrekk = 0;


    public Lønnsmottaker(String identifikator) {
        id = identifikator;
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
//    public void leggTilBesk(String besk, double sum){
//
//        Double verdi = beskrivelse.get(besk);
//        if (verdi == null){
//            beskrivelse.put(besk, sum);
//        }else{beskrivelse.put(besk, verdi+sum);}
//
//    }
//    public void leggTilFordel(String typeFordel, double sum){
//
//        Double verdi = fordel.get(typeFordel);
//        if (verdi == null){
//            beskrivelse.put(typeFordel, sum);
//        }else{beskrivelse.put(typeFordel, verdi+sum);}
////        System.out.println("i legg til fordel");
//    }
//    public void leggTilAga(double sum){
//        utloeserAga += sum;
////        System.out.println("i legg til aga");
//
//    }
//    public void leggTilTrekk(double sum){
//        inngaariGrunnlagForTrekk += sum;
////        System.out.println("i legg til trekk");

//    }
    public void leggTilForskuddstrekk(double sum){
        forskuddstrekk += sum;
    }
    public double hentForskuddstrekk(){
        return forskuddstrekk;
    }
//
//    public double hentTrekk(){
//        return inngaariGrunnlagForTrekk;
//    }
//    public double hentAga(){
//        return utloeserAga;
//    }
//    public double hentBesk(String besk){
//        return beskrivelse.get(besk);
//    }
//    public double hentFordel(String type){
//        return fordel.get(type);
//    }
    @Override
    public String toString(){
//        String strengen = String.format(",Personnummer,%s", id);
        String strengen = "";

        for (int i=0; i<alleLoenn.size(); i++){
            strengen = strengen + alleLoenn.get(i).toString() + "\n";
        }
        

//        Map<String, Double> map = new HashMap<>(beskrivelse);
//        for (Map.Entry<String,Double> set: map.entrySet()){
//            strengen = strengen + String.format(",,%s,%s\n", set.getKey(), set.getValue());
//        }
        //        String strengen = String.format("Ansatt nr, personnummer, Lønnsart nr, dato, Antall, Sats, Beskrivelse (Fordel, skatteTrekk, Aga), Total sum");
        strengen = strengen + String.format(";%s;;;1;%s;Forskuddstrekk (Skattetrekk,false,false); %s\n", id, forskuddstrekk, forskuddstrekk);
        return strengen;
    }

}
