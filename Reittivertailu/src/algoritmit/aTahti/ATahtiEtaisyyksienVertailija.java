/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.aTahti;

import java.util.Comparator;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class ATahtiEtaisyyksienVertailija implements Comparator<Ruutu> {

    private Ruutu maali;
    
    public ATahtiEtaisyyksienVertailija(Ruutu maali) {
        this.maali = maali;
    }
    
    @Override
    public int compare(Ruutu r1, Ruutu r2) {
        return (int) (r1.getEtaisyysAlusta()+etaisyysLoppuun(r1)-r2.getEtaisyysAlusta()-etaisyysLoppuun(r2));
    }

    private double etaisyysLoppuun(Ruutu r) {
        int xEtaisyys = Math.abs(r.getX()-maali.getX());
        int yEtaisyys = Math.abs(r.getY()-maali.getY());
        return xEtaisyys+yEtaisyys;
    }

    
}
