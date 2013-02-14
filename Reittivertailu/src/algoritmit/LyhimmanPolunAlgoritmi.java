/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public abstract class LyhimmanPolunAlgoritmi {
    
    /**
     *Algoritmin käyttämä ruudukko, eli verkko.
     */
    protected Ruudukko ruudukko;
    
    /**
     * Alustaa algoritmin.
     * @param ruudukko Ruudukko jossa algoritmi toimii.
     */
    public LyhimmanPolunAlgoritmi(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }
    
    /**
     * Suorittaa yhden vaiheen algoritmista.
     * Muista alustaa taulukko jos käytät tätä metodia ilman suorita metodia.
     * @return false kun algoritmi on suoritettu
     */
    public abstract boolean etene();
    
    
    /**
     * Suorittaa akgiritmin etenemällä askeleen kerralla
     * ja odottamalla askeleen jälkeen parametrina syötetyn ajan.
     * @param aika millisekunteja etenemisten välissä.
     * @see #etene()
     */
    public void suorita(int aika) {
        alusta();
        while (etene()) {
            try {
                Thread.sleep(aika);
            } catch (InterruptedException ex) {
                Logger.getLogger(AhneLyhimmanPolunAlgoritmi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * vastaa suorita(0);
     * @see #suorita(int) 
     */
    public final void suorita() {
        suorita(0);
    }

    /**
     * Pienentää etäisyyttä naapuriin jos parametrista kasiteltavaRuutu on siihen aikaisemmin määriteltyä lyhyempi matka.
     * @param naapuri Ruutu jonka etäisyyttä ollaan mahdollisesti pienentämässä.
     * @param kasiteltavaRuutu Ruutu johon naapuria verrtaan.
     */
    public abstract void loysaa(Ruutu naapuri, Ruutu kasiteltavaRuutu);
    
    /**
     * Palauttaa solmut siihen tilaan, jossa algoritmi toimii aina oikein.
     * Myös muut algoritmin toimimisen kannalta vaaditut alustukset tulee tehdä tässä metodissa.
     */
    public void alusta() {
        for (Ruutu kasiteltavaRuutu : ruudukko) {
            kasiteltavaRuutu.setEdellinen(null);
            if (kasiteltavaRuutu.equals(ruudukko.getLahto())) {
                kasiteltavaRuutu.setEtaisyysAlusta(0);
            } else {
                kasiteltavaRuutu.setEtaisyysAlusta(Double.POSITIVE_INFINITY);
            }
        }
    }
    
}