/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.dijkstra;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.util.ArrayDeque;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class Dijkstra extends LyhimmanPolunAlgoritmi{
    
    public Dijkstra(Ruudukko ruudukko) {
        super(ruudukko);
        ruutuJono = new ArrayDeque<Ruutu>();
        ruutuJono.add(ruudukko.getLahto());
    }
    
    @Override
    public boolean etene() {
        System.out.println(ruutuJono.size());
        if (ruutuJono.isEmpty()) return false;
        Ruutu kasiteltavaRuutu = ruutuJono.poll();
        for (Ruutu naapuri : kasiteltavaRuutu.getNaapurit()) {
            if (naapuri == null) {
                System.out.println("null");
                continue;
            }
            if (naapuri.onkoEste()) {
                System.out.println("este");
                continue;
            }
            if (naapuri.onkoKasitelty()) {
                System.out.println("kasitelty");
                continue;
            }
            if (naapuri.getEtaisyysAlusta() > kasiteltavaRuutu.getEtaisyysAlusta()+1) {
                naapuri.setEtaisyysAlusta(kasiteltavaRuutu.getEtaisyysAlusta()+1);
            } 
            System.out.println("lisays: "+ruutuJono.offer(naapuri));
        }
        kasiteltavaRuutu.setKasitelty();
        return true;
    }

    @Override
    public Ruutu[] getTulos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
