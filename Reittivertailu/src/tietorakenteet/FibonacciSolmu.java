package tietorakenteet;

import java.util.Map;

/**
 * Fibonacci keon alkio.
 * @author Antti
 */
public class FibonacciSolmu<Comparable, Object> implements Map.Entry<Comparable, Object> {
    int lapsiSolmujenLukumaara;//'aste'
    boolean merkitty;
    FibonacciSolmu vanhempi;
    FibonacciSolmu lapsi;
    FibonacciSolmu vasen;
    FibonacciSolmu oikea;
    Object alkio;//<alkio, avain> (ehkä pitäisi käyttää Comparable)
    Comparable key;
    
    public FibonacciSolmu(Comparable key, Object alkio) {
        //this.key = key;
        //this.alkio = alkio;
        vasen = oikea = this;
    }
    
    /**
     * palauttaa avaimen
     * @return avain
     */
    @Override
    public Comparable getKey() {
        return key;
    }

    /**
     * palauttaa arvon
     * @return arvo
     */
    @Override
    public Object getValue() {
        return alkio;
    }

    /**
     * asettaa arvon
     * @param value arvo
     * @return vanha arvo
     */
    @Override
    public Object setValue(Object value) {
        Object vanha = getValue();
        alkio = value;
        return vanha;
    }


 
}
