package algoritmit.aTahti;

import ruudukko.Ruudukko;

/**
 * Dijkstra algoritmin toteuttava LyhimmänPolunAlgoritmi.
 * @author Antti
 */
public class ATahti extends algoritmit.AhneLyhimmanPolunAlgoritmi {
      
    /**
     * Alustaa algoritmin
     * @param ruudukko Algoritmin käyttämä ruudukko
     */
    public ATahti(Ruudukko ruudukko, JonoTyyppi jonoTyyppi) {
        super(ruudukko, jonoTyyppi, new ATahtiEtaisyyksienVertailija(ruudukko));
    }
    
    
    @Override
    public String toString() {
        return "A*";
    }
    
}
