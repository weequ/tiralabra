package algoritmit.dijkstra;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.util.PriorityQueue;
import ruudukko.Ruudukko;

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
        super(ruudukko, new PriorityQueue<>(11, new DijkstraEtaisyyksienVertailija()));
    }
  
}
