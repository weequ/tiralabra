/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
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
    

    @Override
    public Comparable getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return alkio;
    }

    @Override
    public Object setValue(Object value) {
        Object vanha = getValue();
        alkio = value;
        return vanha;
    }


 
}
