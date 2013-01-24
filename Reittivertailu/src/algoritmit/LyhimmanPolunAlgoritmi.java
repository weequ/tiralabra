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
     * Alustaa algoritmin
     * @param ruudukko Ruudukko, eli verkko, jossa algoritmi etenee.
     */
    public LyhimmanPolunAlgoritmi(Ruudukko ruudukko) {
            this.ruudukko = ruudukko;
    }
    
    /**
     * Hakee lyhimmän polun maalista lähtöön.
     * @return Lyhin polku jossa lahto paallimmaisena ja maali viimeisenä.
     */
    public Stack<Ruutu> getTulos() {
        Ruutu r = ruudukko.getMaali();
        Stack<Ruutu> ruutuPino = new Stack<>();
        while (r!=null) {
            ruutuPino.add(r);
            r = r.getEdellinen();
        }
        return ruutuPino;
    }
    
    /**
     * Suorittaa yhden vaiheen algoritmista.
     * @return false kun algoritmi on suoritettu
     */
    public abstract boolean etene();

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
