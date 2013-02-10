package algoritmit.dijkstra;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.util.PriorityQueue;
import ruudukko.Ruudukko;
import tietorakenteet.BinaariPrioriteettiJono;

/**
 * Dijkstra algoritmin toteuttava LyhimmänPolunAlgoritmi.
 * @author Antti
 */
public class Dijkstra extends LyhimmanPolunAlgoritmi {
   
    
    /**
     * Alustaa algoritmin
     * @param ruudukko Algoritmin käyttämä ruudukko
     */
    public Dijkstra(Ruudukko ruudukko) {
        super(ruudukko, new BinaariPrioriteettiJono<>(11, new DijkstraEtaisyyksienVertailija()));//new PriorityQueue<>(11, new DijkstraEtaisyyksienVertailija()));
    }
  
}
