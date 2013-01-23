/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public abstract class LyhimmanPolunAlgoritmi {
    
    protected Queue<Ruutu> ruutuJono;//Dijkstra: ArrayDequeue, A*: PriorityQueue
    
    /**
     *Algoritmin käyttämä ruudukko, eli verkko.
     */
    protected Ruudukko ruudukko;
    
    
    
    /**
     * Alustaa algoritmin
     * @param ruudukko Ruudukko, eli verkko, jossa algoritmi etenee.
     */
    public LyhimmanPolunAlgoritmi(Ruudukko ruudukko) {
            this.ruudukko = ruudukko;
    }
    
    /**
     * Hakee lyhimmän polun maalista lähtöön.
     * @return Lyhin polku maalista lähtöön.
     */
    public abstract Ruutu[] getTulos();
    
    /**
     * Suorittaa yhden vaiheen algoritmista.
     * @return false kun algoritmi on suoritettu
     */
    public abstract boolean etene();
    
    public void alussa() {
        
    }
    
    public void lopussa() {
        
    }
    
    /**
     * vastaa suorita(0);
     * @see #suorita(int) 
     */
    public final void suorita() {
        suorita(0);
    }
    
    /**
     * Suorittaa akgiritmin etenemällä askeleen kerralla
     * ja odottamalla askeleen jälkeen parametrina syötetyn ajan.
     * @param aika millisekunteja etenemisten välissä.
     * @see #etene()
     */
    public final void suorita(int aika) {
        while (etene()) {
            try {
                Thread.sleep(aika);
            } catch (InterruptedException ex) {
                Logger.getLogger(LyhimmanPolunAlgoritmi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
