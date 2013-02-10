package tietorakenteet;

import java.util.Comparator;
import ruudukko.FibonacciSolmu;

/**
 * EI TOIMI (KESKENERÄINEN)
 * Fibonacci keon toteuttava luokka. 
 * Fibonacci keolla insert ja decrease key saadaan toteutettua vakioajassa toisin kuin esimerkiksi binäärikeolla.
 * Decrease key joutuu kuitenkin välillä tekemään O(log(n)) operaation, mutta niin harvoin ettei se muuta kertaluokkaa.
 * @author Antti
 */
public class FibonacciKeko<T extends FibonacciSolmu> {
    
    private T pienin;
    private int koko;
    private Comparator<? super T> vertailija;
    
    
    /**
     * Luo uuden fibonacci keon.
     */
    public FibonacciKeko(Comparator<? super T> vertailija) {
        this.vertailija = vertailija;
        pienin = null;
        koko = 0;
    }
    
    /**
     * Palauttaa keon pienimmän alkion poistamatta sitä.
     * @return Keon pienin alkio.
     */
    public T findMin() {
        return pienin;
    }
    
    /**
     * Poistaa keon pienimmän alkion ja palauttaa sen sitten.
     * @return Keon pienin alkio.
     */
    public T deleteMin() {
        T tulos = pienin;
        if (tulos == null) return tulos;
        yhdistaVierusListat((T)tulos.getLapsi(), tulos);//kaikki lapset pitaa lisata
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    /**
     * Lisää solmun kekoon.
     * @param solmu Solmu joka lisätään kekoon.
     */
    public void lisaa(T solmu) {
        if (pienin == null) {
            pienin = solmu;
        } else {
            yhdistaVierusListat(solmu, pienin);
            if (vertailija.compare(solmu, pienin) < 0) {
                pienin = solmu;
            }
        }
        koko++;
    }
    
    /**
     * Pienentää alkion avainta.
     * @param o Alkio jonka avainta pienennetään
     * @param key Avaimen uusi arvo. Oltava vanhaa arvoa pienempi.
     */
    public void decreaseKey(T o, Comparable key) {
        //if (o.getKey().compareTo(key) < 0) throw new IllegalArgumentException("Uusi avain on vanhaa isompi.");
        //jatkuu
    }
    
    /**
     * Asettaa lapsi solmun lapseksi ja vanhempi solmun tämän aikuiseksi.
     * @param lapsi Solmu josta tulee lapsi
     * @param vanhempi Solmu josta tulee vanhempi
     */
    private void yhdista(T lapsi, T vanhempi) {
        lapsi.getOikea().setVasen(lapsi.getVasen().getOikea());
        lapsi.getVasen().setOikea(lapsi.getOikea().getVasen());
        lapsi.setVanhempi(vanhempi);
        if (vanhempi.getLapsi() == null) {
            vanhempi.setLapsi(lapsi);
        } else {
            yhdistaVierusListat(lapsi, vanhempi);
        }
        vanhempi.setLapsiSolmujenMaara(vanhempi.getLapsiSolmujenMaara()+1);
        lapsi.setMerkitty(false);
    }
    
    
    private void yhdistaVierusListat(T a, T b) {
        if (a == null || b == null) return;
        T apusolmu = (T)a.getOikea();
        a.setOikea(b.getOikea());
        a.getOikea().setVasen(a);
        b.setOikea(apusolmu);
        b.getOikea().setVasen(b);
        
//        a.vasen = b;
//        a.oikea = b.oikea;
//        b.oikea = a;
//        a.oikea.vasen = a;
    }
    
    /**
     * Yhdistää juuret niin että kaikilla juurilla on eri määrä lapsisolmuja
     */
    private void vakauta() {//eng consolidate
        
    }
}
