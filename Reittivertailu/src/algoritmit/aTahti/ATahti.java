/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.aTahti;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.awt.Point;
import java.util.PriorityQueue;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class ATahti extends LyhimmanPolunAlgoritmi {
    
    private PriorityQueue<Ruutu> ruutuJono;
    
    public ATahti(Ruudukko ruudukko) {
        super(ruudukko);
        ruutuJono = new PriorityQueue<>(11, new ATahtiEtaisyyksienVertailija(ruudukko.getMaali()));
        ruutuJono.add(ruudukko.getLahto());
        ruudukko.getLahto().setKasitelty();
    }
    
    @Override
    public boolean etene() {
        int koko = ruutuJono.size();
        System.out.println(koko);
        if (koko == 0) return false;//Algoritmi ei päässyt loppuun
        Ruutu kasiteltavaRuutu = ruutuJono.poll();
        kasiteltavaRuutu.setKasitelty();
        for (Ruutu naapuri : kasiteltavaRuutu.getNaapurit()) {
            if (naapuri == null) {
                continue;

            }
            if (naapuri.onkoEste()) {
                continue;
            }
            if (naapuri.onkoKasitelty()) {
                continue;
            }
            if (naapuri.getEtaisyysAlusta() > kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus()) {
                naapuri.setEtaisyysAlusta(kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus());
                naapuri.setEdellinen(kasiteltavaRuutu);
            }
            if (naapuri.equals(ruudukko.getMaali())) return false;
            if (!ruutuJono.contains(naapuri)) ruutuJono.offer(naapuri);
        }
        return true;
    }
    
    
}
