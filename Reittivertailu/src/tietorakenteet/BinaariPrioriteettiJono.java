/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Comparator;
import ruudukko.BinaariSolmu;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class BinaariPrioriteettiJono<T extends BinaariSolmu> implements PaivittyvaPrioriteettiJono<T> {
    BinaariKeko<T> bK;

    public BinaariPrioriteettiJono(int aloitusKapasiteetti, Comparator<? super Ruutu> vertailija) {
        bK = new BinaariKeko(aloitusKapasiteetti, vertailija);
    }
    
    @Override
    public void update(T e) {
        bK.paivitaAvain(e.getSijaintiKeossa());
    }

    @Override
    public boolean offer(T e) {
        bK.lisaa(e);
        return true;
    }

    @Override
    public T poll() {
        return (T)bK.poistaPienin();
    }

    @Override
    public int size() {
        return bK.getKoko();
    }

    
}
