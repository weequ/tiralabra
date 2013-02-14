/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import java.util.Iterator;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class BellmanFord extends LyhimmanPolunAlgoritmi{
    int moneskoKertaMenossa;
    
    /**
     * Alustaa algoritmin.
     * @param ruudukko Ruudukko jossa algoritmi toimii.
     */
    public BellmanFord(Ruudukko ruudukko) {
        super(ruudukko);
        this.ruudukko = ruudukko;
        alusta();
    }
    
    /**
     * @see LyhimmanPolunAlgoritmi#alusta() 
     */
    public void alusta() {
        super.alusta();
        moneskoKertaMenossa = 0;
    }
    
    /**
     * @see LyhimmanPolunAlgoritmi#etene() 
     */
    @Override
    public boolean etene() {
        if (moneskoKertaMenossa == ruudukko.getLeveys()*ruudukko.getKorkeus()) {
            return false;
        } else {
            moneskoKertaMenossa++;
        }
        for (Ruutu kasiteltavaRuutu : ruudukko) {
            if (kasiteltavaRuutu.onkoEste()) continue;
            for (Ruutu naapuri: kasiteltavaRuutu.getNaapurit()) {
                if (naapuri == null) continue;
                loysaa(naapuri, kasiteltavaRuutu);
            }
        }
        return true;
    }
    
    /**
     * @see LyhimmanPolunAlgoritmi#suorita(int) 
     */
    @Override
    public void suorita(int aika) {
        super.suorita(aika);
        System.out.println("Etaisyys: "+ruudukko.getMaali().getEtaisyysAlusta());
    }
    
    /**
     * @see LyhimmanPolunAlgoritmi#loysaa(ruudukko.Ruutu, ruudukko.Ruutu) 
     */
    @Override
    public void loysaa(Ruutu naapuri, Ruutu kasiteltavaRuutu) {
        double etaisyysTahanNaapuriin = kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus();
        if (naapuri.getEtaisyysAlusta() > etaisyysTahanNaapuriin) {
            naapuri.setEtaisyysAlusta(etaisyysTahanNaapuriin);
            naapuri.setEdellinen(kasiteltavaRuutu);
        }
    }
    
}
