/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

import java.util.ArrayList;
import javax.swing.event.ChangeListener;

/**
 * Ruudukkoon kuuluva koordinaatti, solmu, jota reitinhakualgoritmit voivat käyttää.
 * @author Antti
 * @see Ruudukko
 */
public class Ruutu {

    private Ruudukko ruudukko;
    private boolean este;
    private boolean kasitelty;
    private int x;
    private int y;
    private double etaisyysAlusta;
        
    public Ruutu(Ruudukko ruudukko, char ruutu, int x, int y) throws Exception {
        this.ruudukko = ruudukko;
        kasitelty = false;
        etaisyysAlusta = Double.POSITIVE_INFINITY;
        switch (ruutu) {
            case '#':
                este = true;
                break;
            case '.':
                este = false;
                break;
            default:
                throw new Exception("Väärä merkki: '"+ruutu+"'. Hyväksytyt merkit: '#' ja '.'");
        }
        
    }
    
    public boolean onkoEste() {
        return este;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Ruutu[] getNaapurit() {
        return new Ruutu[] {
            ruudukko.getRuutu(x-1, y),
            ruudukko.getRuutu(x+1, y),
            ruudukko.getRuutu(x, y-1),
            ruudukko.getRuutu(x, y+1)
        };
    }
    
    public void setEtaisyysAlusta(double etaisyys) {
        this.etaisyysAlusta = etaisyys;
    }
    
    public double getEtaisyysAlusta() {
        return etaisyysAlusta;
    }
    
    public void setKasitelty() {
        kasitelty = true;
    }
    
    public boolean onkoKasitelty() {
        return kasitelty;
    }
 
}
