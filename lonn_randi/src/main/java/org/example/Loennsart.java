package org.example;

public class Loennsart {
    private String beskrivelse;
    private String fordel;
    private boolean inngaariGrunnlagForTrekk;
    private boolean utloeserAga;
    private int art = 10;

    public Loennsart(String besk, String fordel, boolean gTrekk, boolean uAga){
        beskrivelse = besk;
        this.fordel = fordel;
        inngaariGrunnlagForTrekk = gTrekk;
        utloeserAga = uAga;
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
    public int hentArt(){
        return art;
    }
    public void settArt(int lArt){
        art = lArt;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Loennsart)){
            return false;
        }
        Loennsart objekt = (Loennsart) o;
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
        String strengen = String.format("Lønnsart: %s; Beskrivelse: %s; fordel: %s; inngårTrekk: %s; Utløser aga: %s",art ,beskrivelse, fordel, inngaariGrunnlagForTrekk, utloeserAga);
        return strengen;
    }

}
