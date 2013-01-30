/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

/**
 *
 * @author Antti
 */
public class FibonacciKeko {
    
    private FibonacciSolmu pienin;
    private int koko;
    
    public FibonacciKeko() {
        pienin = null;
        koko = 0;
    }
    
    public FibonacciSolmu findMin() {
        return pienin;
    }
    
    public FibonacciSolmu deleteMin() {
        FibonacciSolmu tulos = pienin;
        if (tulos == null) return tulos;
        yhdistaVierusListat(tulos.lapsi, tulos);//kaikki lapset pitaa lisata
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    public void insert(FibonacciSolmu<Comparable, Object> solmu) {
        if (pienin == null) {
            pienin = solmu;
        } else {
            yhdistaVierusListat(solmu, pienin);
            if (solmu.getKey().compareTo(pienin) < 0) {
                pienin = solmu;
            }
        }
        koko++;
    }
    
    public void decreaseKey(FibonacciSolmu<Comparable, Object> o, Comparable key) {
        if (o.getKey().compareTo(key) < 0) throw new IllegalArgumentException("Uusi avain on vanhaa isompi.");
        //jatkuu
    }
    
    /**
     * Asettaa lapsi solmun lapseksi ja vanhempi solmun tämän aikuiseksi.
     * @param lapsi Solmu josta tulee lapsi
     * @param vanhempi Solmu josta tulee vanhempi
     */
    private void yhdista(FibonacciSolmu lapsi, FibonacciSolmu vanhempi) {
        lapsi.oikea.vasen = lapsi.vasen.oikea;
        lapsi.vasen.oikea = lapsi.oikea.vasen;
        lapsi.vanhempi = vanhempi;
        if (vanhempi.lapsi == null) {
            vanhempi.lapsi = lapsi;
        } else {
            yhdistaVierusListat(lapsi, vanhempi);
        }
        vanhempi.lapsiSolmujenLukumaara++;
        lapsi.merkitty = false;
    }
    
    
    private void yhdistaVierusListat(FibonacciSolmu a, FibonacciSolmu b) {
        if (a == null || b == null) return;
        FibonacciSolmu apusolmu = a.oikea;
        a.oikea = b.oikea;
        a.oikea.vasen = a;
        b.oikea = apusolmu;
        b.oikea.vasen = b;
        
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
