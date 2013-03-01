package algoritmit.dijkstra;

import algoritmit.AhneLyhimmanPolunAlgoritmi;
import ruudukko.Ruudukko;
import tietorakenteet.BinaariPrioriteettiJono;

/**
 * Dijkstra algoritmin toteuttava LyhimmänPolunAlgoritmi.
 * @author Antti
 */
public class Dijkstra extends AhneLyhimmanPolunAlgoritmi {
   
    
    /**
     * Alustaa algoritmin
     * @param ruudukko Algoritmin käyttämä ruudukko
     */
    public Dijkstra(Ruudukko ruudukko, JonoTyyppi jonoTyyppi) {
        super(ruudukko, jonoTyyppi, new DijkstraEtaisyyksienVertailija());//new PriorityQueue<>(11, new DijkstraEtaisyyksienVertailija()));
    }
    
    @Override
    public String toString() {
        return "Dijkstra";
    }
  
}
