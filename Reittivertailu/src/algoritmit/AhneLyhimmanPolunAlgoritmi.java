/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import ruudukko.Ruudukko;
import ruudukko.Ruutu;
import tietorakenteet.PaivittyvaPrioriteettiJono;

/**
 * Malli ahneelle lyhimmän polun hakualgoritmeille. Dijkstra ja A* toimivat tällä, mutta eri parametreilla.
 * @author Antti
 */
public class AhneLyhimmanPolunAlgoritmi extends LyhimmanPolunAlgoritmi{
    
    private PaivittyvaPrioriteettiJono<Ruutu> ruutuJono;
    
    /**
     * Alustaa algoritmin
     * @param ruudukko Ruudukko, eli verkko, jossa algoritmi etenee.
     * @param ruutuJono PrioriteettiJono joka järjestää solmuja.
     */
    public AhneLyhimmanPolunAlgoritmi(Ruudukko ruudukko, PaivittyvaPrioriteettiJono ruutuJono) {
            super(ruudukko);
            this.ruutuJono = ruutuJono;
            alusta();
    }

    
    /**
     * @see LyhimmanPolunAlgoritmi#etene() 
     */
    @Override
    public boolean etene() {
        int koko = ruutuJono.size();
        //System.out.println(koko);
        if (koko == 0) return false;//Algoritmi ei päässyt loppuun
        Ruutu kasiteltavaRuutu = ruutuJono.poll();
        kasiteltavaRuutu.setVaihe(Ruutu.Vaihe.KASITELTY);
        if (kasiteltavaRuutu.equals(ruudukko.getMaali())) return false;//uusi
        for (Ruutu naapuri : kasiteltavaRuutu.getNaapurit()) {
            if (naapuri == null || naapuri.getVaihe() == Ruutu.Vaihe.KASITELTY || naapuri.onkoEste()){
                continue;
            }
            loysaa(naapuri, kasiteltavaRuutu);
        }
        return true;
    }
    
    /**
     * @see LyhimmanPolunAlgoritmi#loysaa(ruudukko.Ruutu, ruudukko.Ruutu) 
     */
    @Override
    public void loysaa(Ruutu naapuri, Ruutu kasiteltavaRuutu) {
        double etaisyysTahanNaapuriin = kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus();
        if (naapuri.getEtaisyysAlusta() > etaisyysTahanNaapuriin) {
            naapuri.setEtaisyysAlusta(etaisyysTahanNaapuriin);
            naapuri.setEdellinen(kasiteltavaRuutu);
            if (naapuri.getVaihe() == Ruutu.Vaihe.KASITTELYSSA) { //Koska javan priorityqueue ei tue päivitystä!
                ruutuJono.update(naapuri);
                //ruutuJono.remove(naapuri);//O(n)
                //ruutuJono.offer(naapuri);//Binäärikeko: O(log(n) Fibonacci keko: O(1)
            } else {
                ruutuJono.offer(naapuri);
                naapuri.setVaihe(Ruutu.Vaihe.KASITTELYSSA);
            }
        }
    }
    
    
    /**
     * @see LyhimmanPolunAlgoritmi#suorita(int) 
     */
    public final void suorita(int aika) {
        super.suorita(aika);
        if (ruutuJono.size() == 0) {
            System.out.println("Ei reittiä lähdön ja maalin välillä!");
        } else {
            System.out.println("Reitti maaliin löydetty. Reitin pituus: "+ruudukko.getMaali().getEtaisyysAlusta());
        }
    }
    
    /**
     * @see LyhimmanPolunAlgoritmi#alusta() 
     */
    public void alusta() {
        super.alusta();
        ruutuJono.clear();
        for (Ruutu ruutu : ruudukko) {
            ruutu.setVaihe(Ruutu.Vaihe.KASITTELEMATON);
        }
        ruutuJono.offer(ruudukko.getLahto());
        ruudukko.getLahto().setVaihe(Ruutu.Vaihe.KASITTELYSSA);
    }
}
