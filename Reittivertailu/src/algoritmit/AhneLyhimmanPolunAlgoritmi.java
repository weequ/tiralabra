/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import java.util.Comparator;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;
import tietorakenteet.BinaariPrioriteettiJono;
import tietorakenteet.PaivittyvaPrioriteettiJono;
import tietorakenteet.PaivittyvaPriorityQueue;

/**
 * Malli ahneelle lyhimmän polun hakualgoritmeille. Dijkstra ja A* toimivat tällä, mutta eri parametreilla.
 * @author Antti
 */
public abstract class AhneLyhimmanPolunAlgoritmi extends LyhimmanPolunAlgoritmi{
    
    private PaivittyvaPrioriteettiJono<Ruutu> ruutuJono;
    
    /**
     * Tyyppejä ova BINAARI ja JAVA_PRIORITYQUEUE. JAVA_PRIORITYQUEUE ei kuitenkaan kuulu kurssiin.
     */
    public static enum JonoTyyppi {BINAARI, JAVA_PRIORITYQUEUE};
    /**
     * Alustaa algoritmin
     * @param ruudukko Ruudukko, eli verkko, jossa algoritmi etenee.
     * @param ruutuJono PrioriteettiJono joka järjestää solmuja.
     */
    public AhneLyhimmanPolunAlgoritmi(Ruudukko ruudukko, JonoTyyppi jonoTyyppi, Comparator<Ruutu> vertailija) {
            this(ruudukko, jonoTyyppi, vertailija, 11);
    }
    
    
    public AhneLyhimmanPolunAlgoritmi(Ruudukko ruudukko, JonoTyyppi jonoTyyppi, Comparator<Ruutu> vertailija, int aloitusKapasiteetti) {
        super(ruudukko);
        switch (jonoTyyppi) {
            case BINAARI:
                this.ruutuJono = new BinaariPrioriteettiJono<>(aloitusKapasiteetti, vertailija);
                break;
            case JAVA_PRIORITYQUEUE:
                this.ruutuJono = new PaivittyvaPriorityQueue<>(aloitusKapasiteetti, vertailija);
                break;
        }
    }
    

    
    /**
     * @see LyhimmanPolunAlgoritmi#etene() 
     */
    @Override
    public boolean etene() {
        if (ruudukko.getMaali().getVaihe() == Ruutu.Vaihe.KASITELTY) return false;
        int koko = ruutuJono.size();
        if (koko == 0) return false;//Algoritmi ei päässyt loppuun
        Ruutu kasiteltavaRuutu = ruutuJono.poll();
        kasiteltavaRuutu.setVaihe(Ruutu.Vaihe.KASITELTY);
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
    @Override
    public final void suorita(int aika) {
        super.suorita(aika);
        if (ruutuJono.size() == 0) {
            //System.out.println("Ei reittiä lähdön ja maalin välillä!");
        } else {
            //System.out.println("Reitti maaliin löydetty. Reitin pituus: "+ruudukko.getMaali().getEtaisyysAlusta());
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
