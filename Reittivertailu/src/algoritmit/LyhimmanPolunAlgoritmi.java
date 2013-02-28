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
    private Thread animoivaSaie;
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
    public void suorita(final int aika) {
        //alusta();
        lopetaAnimointi();
        Runnable suoritettava = new Runnable() {
            @Override
            public void run() {
                while (etene()) {
                    try {
                        Thread.sleep(aika);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        };
        animoivaSaie = new Thread(suoritettava);
        animoivaSaie.start();
    }
    

    /**
     * Lopettaa mahdollisesti käynnissä olevan suorita(int aika) metodin.
     * Tällä metodilla saattaa kestää jonkun aikaa. Maksimissaan suorita metodille parametrina annettu aika + yhteen etene askeleeseen kuluma aika.
     * Parempi tapa olisi varmaankin käyttää Thread.interrupt();
     * @see #suorita(int) 
     */
    public void lopetaAnimointi() {
        if (animoivaSaie != null) animoivaSaie.interrupt();
    }
    
    /**
     * vastaa suorita(0);
     * @return Suorituksen kulunut aika millisekunteina.
     * @see #suorita(int) 
     */
    public final long suorita() {
        long aloitusAika = System.currentTimeMillis();
        while (etene()) {}
        return System.currentTimeMillis()-aloitusAika;
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