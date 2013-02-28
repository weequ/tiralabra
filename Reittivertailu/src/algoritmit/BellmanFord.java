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
     * Tutkii löytyykö ruudukosta negatiivinen sykli.
     * @return True jos löytyy
     */
    private boolean onkoNegatiivisiaSykleja() {
        for (Ruutu kasiteltavaRuutu : ruudukko) {
            if (kasiteltavaRuutu.onkoEste()) continue;
            for (Ruutu naapuri: kasiteltavaRuutu.getNaapurit()) {
                if (naapuri == null) continue;
                if (kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus() < naapuri.getEtaisyysAlusta()) {
                    return true;
                }
            }
        }
        return false;
    }
     
    /**
     * @return true jos on negatiivinen sykli löytyy.
     * @see LyhimmanPolunAlgoritmi#suorita(int) 
     */
    public boolean suoritaJaTutkiOnkoNegatiivisiaSyksleja(int aika) {
        suorita(aika);
        return onkoNegatiivisiaSykleja();
    }
    
    /**
     * @return true jos negatiivinen sykli löytyy 
     * @see LyhimmanPolunAlgoritmi#suorita() 
     */
    public boolean suoritaJaTutkiOnkoNegatiivisiaSyksleja() {
        suorita(0);
        return onkoNegatiivisiaSykleja();
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
