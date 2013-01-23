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
        for (int i = 0; i < koko; i++) {
            Ruutu kasiteltavaRuutu = ruutuJono.poll();
            kasiteltavaRuutu.setKasitelty();
            for (Ruutu naapuri : kasiteltavaRuutu.getNaapurit()) {
                if (naapuri == null) {
                    //System.out.println("null");
                    continue;
                }
                if (naapuri.onkoEste()) {
                    //System.out.println("este");
                    continue;
                }
                if (naapuri.onkoKasitelty()) {
                    //System.out.println("kasitelty");
                    continue;
                }
                if (naapuri.getEtaisyysAlusta() > kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus()) {
                    naapuri.setEtaisyysAlusta(kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus());
                    naapuri.setEdellinen(kasiteltavaRuutu);
                }
                if (!ruutuJono.contains(naapuri)) ruutuJono.offer(naapuri);
                //System.out.println("lisays: "+ruutuJono.offer(naapuri));
            }
        }
        return true;
    }
    
    
}
