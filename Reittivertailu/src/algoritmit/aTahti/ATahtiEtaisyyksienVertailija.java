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
    
    private int xEtaisyys1, yEtaisyys1, xEtaisyys2, yEtaisyys2;
    private double etaisyysLoppuun1, etaisyysLoppuun2;
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
        paivitaArvot(r1, r2);
        
        double g1 = r1.getEtaisyysAlusta()+etaisyysLoppuun1;
        double g2 = r2.getEtaisyysAlusta()+etaisyysLoppuun2;
        //System.out.println((g1-g2));
        if (g1 < g2) {
            return -1;
        } else if (g1 > g2) {
            return 1;
        } else {
            if (etaisyysLoppuun1 < etaisyysLoppuun2) {
                return -1;
            } else if (etaisyysLoppuun1 > etaisyysLoppuun2){
                return +1;
            } else {
                if (Math.max(xEtaisyys1, yEtaisyys1) < Math.max(xEtaisyys2, yEtaisyys2)) {
                    return -1;
                } else if (Math.max(xEtaisyys1, yEtaisyys1) > Math.max(xEtaisyys2, yEtaisyys2)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }//(r1.getEtaisyysAlusta() > r2.getEtaisyysAlusta()){//
    }//(r1.getEtaisyysAlusta() < r2.getEtaisyysAlusta()){//

    
    private void paivitaArvot(Ruutu r1, Ruutu r2) {
        xEtaisyys1 = Math.abs(r1.getX()-maali.getX());
        yEtaisyys1 = Math.abs(r1.getY()-maali.getY());
        xEtaisyys2 = Math.abs(r2.getX()-maali.getX());
        yEtaisyys2 = Math.abs(r2.getY()-maali.getY());
        etaisyysLoppuun1 = (xEtaisyys1+yEtaisyys1);
        etaisyysLoppuun2 = (xEtaisyys2+yEtaisyys2);
    }
    
//    private double etaisyysLoppuun(Ruutu r) {
//        xEtaisyys = Math.abs(r.getX()-maali.getX());
//        yEtaisyys = Math.abs(r.getY()-maali.getY());
//        return (xEtaisyys+yEtaisyys);//Math.sqrt(yEtaisyys*xEtaisyys+yEtaisyys*yEtaisyys);
//    }

    
}
