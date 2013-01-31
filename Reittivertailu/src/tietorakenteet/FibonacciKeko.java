package tietorakenteet;

/**
 * Fibonacci keon toteuttava luokka. 
 * Fibonacci keolla insert ja decrease key saadaan toteutettua vakioajassa toisin kuin esimerkiksi binäärikeolla.
 * Decrease key joutuu kuitenkin välillä tekemään O(log(n)) operaation, mutta niin harvoin ettei se muuta kertaluokkaa.
 * @author Antti
 */
public class FibonacciKeko {
    
    private FibonacciSolmu pienin;
    private int koko;
    
    
    /**
     * Luo uuden fibonacci keon.
     */
    public FibonacciKeko() {
        pienin = null;
        koko = 0;
    }
    
    /**
     * Palauttaa keon pienimmän alkion poistamatta sitä.
     * @return Keon pienin alkio.
     */
    public FibonacciSolmu findMin() {
        return pienin;
    }
    
    /**
     * Poistaa keon pienimmän alkion ja palauttaa sen sitten.
     * @return Keon pienin alkio.
     */
    public FibonacciSolmu deleteMin() {
        FibonacciSolmu tulos = pienin;
        if (tulos == null) return tulos;
        yhdistaVierusListat(tulos.lapsi, tulos);//kaikki lapset pitaa lisata
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    /**
     * Lisää solmun kekoon.
     * @param solmu Solmu joka lisätään kekoon.
     */
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
    
    /**
     * Pienentää alkion avainta.
     * @param o Alkio jonka avainta pienennetään
     * @param key Avaimen uusi arvo. Oltava vanhaa arvoa pienempi.
     */
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
