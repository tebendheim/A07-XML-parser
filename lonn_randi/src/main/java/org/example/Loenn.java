package org.example;

public class Loenn {
    Loennsart art;

    private double antall = 0;

    private double totalSum = 0;

    private double sats = 0;
    private String dato;
    private Lønnsmottaker mottaker;
    public Loenn(Loennsart loennsart, String dato, Lønnsmottaker mottaker){
        art = loennsart;
        this.dato = dato;
        this.mottaker = mottaker;
    }
    public void okSum(double sum){
        totalSum += sum;
    }

    public void oppdaterSats(){
        sats = totalSum/antall;
        }
    public void redSum(double sum){
        totalSum -= sum;
    }
    public void okAntall(double sum){
        antall += sum;
    }
    public void redAntall(double sum){
        antall -= sum;
    }
    public String hentBeskrivelse(){
        return art.hentBeskrivelse();
    }
    public String hentFordel(){
        return art.hentFordel();
    }
    public boolean hentTrekk(){
        return art.hentTrekk();
    }
    public boolean hentAga(){
        return art.hentAga();
    }
    public double hentTotalSum(){
        return totalSum;
    }
    public double hentAntall(){
        return antall;
    }
    public void merge(Loenn nyLoenn){
        if (equals(nyLoenn)){
            totalSum += nyLoenn.hentTotalSum();
            antall += nyLoenn.hentAntall();
        }
    }
    public Lønnsmottaker hentMottaker(){
        return mottaker;
    }
    public Loennsart hentArt(){
        return art;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Loenn)){
            return false;
        }
        Loenn objekt = (Loenn) o;

        if (mottaker.equals(objekt.hentMottaker()) &&  art.equals(objekt.hentArt())){
            return true;
        }

        return false;
    }
//    @Todo: Husk å endre denne slik at linjene blir riktig med skjemaet.

    @Override
    public String toString(){
//        String strengen = String.format("Ansatt nr, personnummer, Lønnsart nr, Antall, Sats, Beskrivelse (Fordel, skatteTrekk, Aga), Total sum");

        String strengen = String.format("");

        if (antall != 0){
            oppdaterSats();
//            @Todo: legg inn sats.
            strengen = strengen + String.format("%s;%s;%s;%s;%s;%s;%s (%s, %s, %s);%s",mottaker.hentAnsattNr(), "'"+mottaker.hentId(), art.hentArt(), dato, Double.toString(antall).replace(".",","), Double.toString(sats).replace(".",","), art.hentBeskrivelse(), art.hentFordel(), art.hentTrekk(), art.hentAga(), Double.toString(totalSum).replace(".",","));

        }else {
//            @Todo: bruk totalsum og sett antall til 1:
            strengen = strengen + String.format("%s;%s;%s;%s;%s;%s;%s (%s, %s, %s);%s",mottaker.hentAnsattNr(), "'"+mottaker.hentId(), art.hentArt(), dato, "1", Double.toString(totalSum).replace(".",","), art.hentBeskrivelse(), art.hentFordel(), art.hentTrekk(), art.hentAga(), Double.toString(totalSum).replace(".",","));
        }
        return strengen;
    }

}
