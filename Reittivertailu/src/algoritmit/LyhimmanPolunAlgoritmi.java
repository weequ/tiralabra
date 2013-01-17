/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import ruudukko.Ruudukko;

/**
 *
 * @author Antti
 */
public abstract class LyhimmanPolunAlgoritmi {
    
    /**
     *
     */
    protected Ruudukko ruudukko;
    
    public LyhimmanPolunAlgoritmi(Ruudukko ruudukko) {
            this.ruudukko = ruudukko;
    }
    
    public abstract boolean etene();
    
    
    public final void suorita() {
        while (etene()) {
        }
    }
}
