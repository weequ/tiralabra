package reittivertailu;

import algoritmit.AhneLyhimmanPolunAlgoritmi;
import algoritmit.BellmanFord;
import algoritmit.LyhimmanPolunAlgoritmi;
import algoritmit.aTahti.ATahti;
import algoritmit.dijkstra.Dijkstra;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.GUI;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;
import tiedostonLuku.Lukija;

/**
 * Vertailuluokka
 * @author Antti
 */
public class Reittivertailu {

    private Ruudukko ruudukko;
    private LyhimmanPolunAlgoritmi algoritmi;
    int ruudukonLeveys, ruudukonKorkeus;
    double esteenTn;
    
    public Reittivertailu(int ruudukonLeveys, int ruudukonKorkeus, double esteenTn) {
        if (ruudukonLeveys < 20 || ruudukonKorkeus < 20) {
            throw new IllegalArgumentException("Ruudukon oltava kooltaan vähintään 20X20.");
        }
        this.ruudukonLeveys = ruudukonLeveys;
        this.ruudukonKorkeus = ruudukonKorkeus;
        this.esteenTn = esteenTn;
        this.ruudukko = new Ruudukko(ruudukonLeveys, ruudukonKorkeus, esteenTn);
    }
    
    private void arvoRuudukko() {
        int leveys = ruudukko.getLeveys();
        int korkeus = ruudukko.getKorkeus();
        do {
            ruudukko.arvoEsteet(esteenTn);
        } while (!
                    (ruudukko.setLahtoTyhjaan(2, 2, 10, 10) && 
                     ruudukko.setMaaliTyhjaan(leveys-10, korkeus-10, leveys-2, korkeus-2))
                );//Arvotaan kunnes lähtö ja maali saadaan asetettua tyhjään ruutuun.
    }
    
    private long mittaaSuoritusAika(LyhimmanPolunAlgoritmi algoritmi) {
        return algoritmi.suorita();
    }
    
    public void vertaa(int aika) {
        long kokoAika = 0;
        double[] kokoAjat = new double[3];
        int[] tulokset = new int[3];
        while (true) {
            arvoRuudukko();
            LyhimmanPolunAlgoritmi[] algoritmit = {new Dijkstra(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI),
                new ATahti(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI),
                new BellmanFord(ruudukko)};
            for (int i = 0; i < algoritmit.length; i++) {
                if (kokoAjat[i] > aika) continue;
                algoritmit[i].alusta();
                kokoAjat[i] += mittaaSuoritusAika(algoritmit[i]);
                if (kokoAjat[i] < aika) tulokset[i]++;
            }
            int montako = 0;
            for (int i = 0; i < kokoAjat.length; i++) {
                if (kokoAjat[i] > aika) montako++;
            }
            if (montako == 3) break;
        }
        for (int i = 0; i < kokoAjat.length; i++) {
            System.out.println("Tulos " + i + ":" + tulokset[i]);
        }
    }
    
}
