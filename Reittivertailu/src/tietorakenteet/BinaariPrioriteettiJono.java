/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Comparator;
import ruudukko.BinaariSolmu;
import ruudukko.Ruutu;

/**
 * Binäärikeolla toteutettu päivittyvä prioriteettijono.
 * @author Antti
 */
public class BinaariPrioriteettiJono<T extends BinaariSolmu> implements PaivittyvaPrioriteettiJono<T> {
    BinaariKeko<T> bK;

    public BinaariPrioriteettiJono(int aloitusKapasiteetti, Comparator<? super Ruutu> vertailija) {
        bK = new BinaariKeko(aloitusKapasiteetti, vertailija);
    }
    
    /**
     * @see PaivittyvaPrioriteettiJono#update(java.lang.Object) 
     */
    @Override
    public void update(T e) {
        bK.paivitaAvain(e.getSijaintiKeossa());
    }

    /**
     * @see PaivittyvaPrioriteettiJono#offer(java.lang.Object) 
     */
    @Override
    public boolean offer(T e) {
        bK.lisaa(e);
        return true;
    }
    
    /**
     * @see PaivittyvaPrioriteettiJono#poll() 
     */
    @Override
    public T poll() {
        return (T)bK.poistaPienin();
    }

    /**
     * @see PaivittyvaPrioriteettiJono#size() 
     */
    @Override
    public int size() {
        return bK.getKoko();
    }

    /**
     * @see PaivittyvaPrioriteettiJono#clear() 
     */
    @Override
    public void clear() {
        while (bK.getKoko() > 0) {
            bK.poistaPienin();
        }
    }

    
}
