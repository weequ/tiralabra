/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 * Malli lyhimmän polun hakualgoritmeille. Maarittelee niille yhteisiä ominaisuuksia sekä toteuttaa osan niistä.
 * @author Antti
 */
public abstract class LyhimmanPolunAlgoritmi {
    
    /**
     *Algoritmin käyttämä ruudukko, eli verkko.
     */
    protected Ruudukko ruudukko;
    
    private Queue<Ruutu> ruutuJono;
    
    /**
     * Alustaa algoritmin
     * @param ruudukko Ruudukko, eli verkko, jossa algoritmi etenee.
     */
    public LyhimmanPolunAlgoritmi(Ruudukko ruudukko, PriorityQueue ruutuJono) {
            this.ruudukko = ruudukko;
            this.ruutuJono = ruutuJono;
            ruutuJono.offer(ruudukko.getLahto());
            ruudukko.getLahto().setVaihe(Ruutu.Vaihe.KASITTELYSSA);
    }
    
    /**
     * Hakee lyhimmän polun maalista lähtöön.
     * @return Lyhin polku jossa lahto paallimmaisena ja maali viimeisenä.
     */
    public Stack<Ruutu> getTulos() {
        Ruutu r = ruudukko.getMaali();
        Stack<Ruutu> ruutuPino = new Stack<>();
        while (r!=null) {
            ruutuPino.add(r);
            r = r.getEdellinen();
        }
        return ruutuPino;
    }
    
    /**
     * Suorittaa yhden vaiheen algoritmista.
     * @return false kun algoritmi on suoritettu
     */
    //public abstract boolean etene();
    public boolean etene() {
        int koko = ruutuJono.size();
        System.out.println(koko);
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
    
    
    public void loysaa(Ruutu naapuri, Ruutu kasiteltavaRuutu) {
        double etaisyysTahanNaapuriin = kasiteltavaRuutu.getEtaisyysAlusta()+naapuri.getKustannus();
        if (naapuri.getEtaisyysAlusta() > etaisyysTahanNaapuriin) {
            naapuri.setEtaisyysAlusta(etaisyysTahanNaapuriin);
            naapuri.setEdellinen(kasiteltavaRuutu);
            if (naapuri.getVaihe() == Ruutu.Vaihe.KASITTELYSSA) { //Koska javan priorityqueue ei tue päivitystä!
                ruutuJono.remove(naapuri);//O(n)
                ruutuJono.offer(naapuri);//Binäärikeko: O(log(n) Fibonacci keko: O(1)
            } else {
                ruutuJono.offer(naapuri);
                naapuri.setVaihe(Ruutu.Vaihe.KASITTELYSSA);
            }
        }
    }
    
    
    /**
     * vastaa suorita(0);
     * @see #suorita(int) 
     */
    public final void suorita() {
        suorita(0);
    }
    
    /**
     * Suorittaa akgiritmin etenemällä askeleen kerralla
     * ja odottamalla askeleen jälkeen parametrina syötetyn ajan.
     * @param aika millisekunteja etenemisten välissä.
     * @see #etene()
     */
    public final void suorita(int aika) {
        while (etene()) {
            try {
                Thread.sleep(aika);
            } catch (InterruptedException ex) {
                Logger.getLogger(LyhimmanPolunAlgoritmi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ruutuJono.size() == 0) {
            System.out.println("Ei reittiä lähdön ja maalin välillä!");
        } else {
            System.out.println("Reitti maaliin löydetty. Reitin pituus: "+ruudukko.getMaali().getEtaisyysAlusta());
        }
    }
}
