package algoritmit.aTahti;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.util.PriorityQueue;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;
import tietorakenteet.BinaariPrioriteettiJono;
import tietorakenteet.PaivittyvaPriorityQueue;

/**
 * Dijkstra algoritmin toteuttava LyhimmänPolunAlgoritmi.
 * @author Antti
 */
public class ATahti extends LyhimmanPolunAlgoritmi {
    
    
    /**
     * Alustaa algoritmin
     * @param ruudukko Algoritmin käyttämä ruudukko
     */
    public ATahti(Ruudukko ruudukko) {
        super(ruudukko, new BinaariPrioriteettiJono<>(11, new ATahtiEtaisyyksienVertailija(ruudukko.getMaali())));
    }
    
}
