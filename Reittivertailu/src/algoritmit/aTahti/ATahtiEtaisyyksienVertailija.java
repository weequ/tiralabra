/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.aTahti;

import java.util.Comparator;
import ruudukko.Ruutu;

/**
 * Solmujen vertailija ATähti algoritmille.
 * @author Antti
 */
public class ATahtiEtaisyyksienVertailija implements Comparator<Ruutu> {

    private Ruutu maali;
    
    /**
     * Luo etäisyysvertailijan
     * @param maali Ruudukon maaliruutu
     */
    public ATahtiEtaisyyksienVertailija(Ruutu maali) {
        this.maali = maali;
    }
    
    /**
     * Vertaa ruutuja keskenään
     * @param r1 Ruutu jota verrataan
     * @param r2 Ruutu johon verrataan
     * @return
     */
    @Override
    public int compare(Ruutu r1, Ruutu r2) {
        //return (int) ((r1.getEtaisyysAlusta()+etaisyysLoppuun(r1))-(r2.getEtaisyysAlusta()+etaisyysLoppuun(r2)));
        double g1 = r1.getEtaisyysAlusta()+etaisyysLoppuun(r1);
        double g2 = r2.getEtaisyysAlusta()+etaisyysLoppuun(r2);
        //System.out.println((g1-g2));
        if (g1 < g2) {
            return -1;
        } else if (g1 > g2) {
            return 1;
        } else {
            if (etaisyysLoppuun(r1) < etaisyysLoppuun(r2)) {
                return -1;
            } else if (etaisyysLoppuun(r1) > etaisyysLoppuun(r2)){
                return +1;
            } else {
                return 0;
            }
        }//(r1.getEtaisyysAlusta() > r2.getEtaisyysAlusta()){//
    }//(r1.getEtaisyysAlusta() < r2.getEtaisyysAlusta()){//

    private double etaisyysLoppuun(Ruutu r) {
        int xEtaisyys = Math.abs(r.getX()-maali.getX());
        int yEtaisyys = Math.abs(r.getY()-maali.getY());
        return (xEtaisyys+yEtaisyys);//Math.sqrt(yEtaisyys*xEtaisyys+yEtaisyys*yEtaisyys);
    }

    
}
