package org.example;

public class Loenn {
    private String beskrivelse;
    private String fordel;
    private double antall = 0;
    private boolean inngaariGrunnlagForTrekk;
    private boolean utloeserAga;

    private double totalSum = 0;

    private double sats = 0;
    private String id;
    public Loenn(String besk, String fordel, boolean gTrekk, boolean uAga, String id){
        beskrivelse = besk;
        this.fordel = fordel;
        inngaariGrunnlagForTrekk = gTrekk;
        utloeserAga = uAga;
        this.id = id;
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
        return beskrivelse;
    }
    public String hentFordel(){
        return fordel;
    }
    public boolean hentTrekk(){
        return inngaariGrunnlagForTrekk;
    }
    public boolean hentAga(){
        return utloeserAga;
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

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Loenn)){
            return false;
        }
        Loenn objekt = (Loenn) o;
        String oBesk = objekt.hentBeskrivelse();
        String oFordel = objekt.hentFordel();
        boolean oAga = objekt.hentAga();
        boolean oTrekk = objekt.hentTrekk();

        if (oAga == utloeserAga && oTrekk == inngaariGrunnlagForTrekk && fordel.equals(oFordel) && beskrivelse.equals(oBesk)){
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
            strengen = strengen + String.format(";%s;;;%s;%s;%s (%s, %s, %s);%s", id, antall, sats, beskrivelse, fordel, inngaariGrunnlagForTrekk, utloeserAga, totalSum);

        }else {
//            @Todo: bruk totalsum og sett antall til 1:
            strengen = strengen + String.format(";%s;;;%s;%s;%s (%s, %s, %s);%s", id, 1, totalSum, beskrivelse, fordel, inngaariGrunnlagForTrekk, utloeserAga, totalSum);
        }
        return strengen;
    }

}
