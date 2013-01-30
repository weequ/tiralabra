/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.dijkstra;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.util.HashMap;
import java.util.PriorityQueue;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class Dijkstra extends LyhimmanPolunAlgoritmi {
    
    private PriorityQueue<Ruutu> ruutuJono;
    
    public Dijkstra(Ruudukko ruudukko) {
        super(ruudukko);
        ruutuJono = new PriorityQueue<>(11, new DijkstraEtaisyyksienVertailija());
        ruutuJono.offer(ruudukko.getLahto());
        ruudukko.getLahto().setVaihe(Ruutu.Vaihe.KASITTELYSSA);
    }
    
    @Override
    public boolean etene() {
        int koko = ruutuJono.size();//O(1)
        System.out.println(koko);
        if (koko == 0) return false;//Algoritmi ei päässyt loppuun
        for (int i = 0; i < koko; i++) {
            Ruutu kasiteltavaRuutu = ruutuJono.poll();//O(log(n))
            kasiteltavaRuutu.setVaihe(Ruutu.Vaihe.KASITELTY);
            for (Ruutu naapuri : kasiteltavaRuutu.getNaapurit()) {
                if (naapuri == null) {
                    continue;
                }
                if (naapuri.onkoEste()) {
                    continue;
                }
                if (naapuri.getVaihe() == Ruutu.Vaihe.KASITELTY) {
                    continue;
                }
                if (naapuri.getEtaisyysAlusta() > kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus()) {
                    naapuri.setEtaisyysAlusta(kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus());//Decrease key. Binäärikeko: O(log(n) Fibonacci keko: O(1)
                    naapuri.setEdellinen(kasiteltavaRuutu);
                    if (naapuri.getVaihe() == Ruutu.Vaihe.KASITTELYSSA) { //Koska javan priorityqueue ei tue päivitystä!
                        ruutuJono.remove(naapuri);//O(n)
                        ruutuJono.offer(naapuri);//Binäärikeko: O(log(n) Fibonacci keko: O(1)
                    }
                }
                if (naapuri.getVaihe() != Ruutu.Vaihe.KASITTELYSSA) {//(!ruutuJono.contains(naapuri)) {
                    ruutuJono.offer(naapuri);//Binäärikeko: O(log(n) Fibonacci keko: O(1)
                    naapuri.setVaihe(Ruutu.Vaihe.KASITTELYSSA);
                }
                //System.out.println("lisays: "+ruutuJono.offer(naapuri));
            }
        }
        return true;
    }
    
}
