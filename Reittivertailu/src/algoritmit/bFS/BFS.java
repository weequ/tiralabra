/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.bFS;

import algoritmit.LyhimmanPolunAlgoritmi;
import java.util.ArrayDeque;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class BFS extends LyhimmanPolunAlgoritmi{
    
    public BFS(Ruudukko ruudukko) {
        super(ruudukko);
        ruutuJono = new ArrayDeque<Ruutu>();
        ruutuJono.add(ruudukko.getLahto());
        ruudukko.getLahto().setKasitelty();
    }
    
    @Override
    public boolean etene() {
        int koko = ruutuJono.size();
        System.out.println(koko);
        if (koko == 0) return false;
        for (int i = 0; i < koko; i++) {
            Ruutu kasiteltavaRuutu = ruutuJono.poll();
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
                //if (naapuri.getEtaisyysAlusta() > kasiteltavaRuutu.getEtaisyysAlusta()+1) {
                naapuri.setEtaisyysAlusta(kasiteltavaRuutu.getEtaisyysAlusta()+1);
                //} 
                naapuri.setKasitelty();
                ruutuJono.offer(naapuri);
                //System.out.println("lisays: "+ruutuJono.offer(naapuri));
            }
        }
        return true;
    }

    
    
}
