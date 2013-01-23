/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;


/**
 * Ruudukkoon kuuluva koordinaatti, solmu, jota reitinhakualgoritmit voivat käyttää.
 * @author Antti
 * @see Ruudukko
 */
public class Ruutu {

    private Ruudukko ruudukko;
    private boolean este;
    private boolean kasitelty;
    private int kustannus;
    private int x;
    private int y;
    private double etaisyysAlusta;
    private Ruutu edellinen;
        
    public Ruutu(Ruudukko ruudukko, char ruutu, int x, int y) throws Exception {
        this.ruudukko = ruudukko;
        edellinen = null;
        this.x = x;
        this.y = y;
        kasitelty = false;
        etaisyysAlusta = Double.POSITIVE_INFINITY;
        if (ruutu >= '0' && ruutu <= '9') {
            este = false;
            kustannus = ruutu-'0';
        } else {
            switch (ruutu) {
                case '-': kustannus = -1;
                case '#':
                    este = true;
                    break;
                case '.':
                    kustannus = 1;
                    este = false;
                    break;
                default:
                    throw new Exception("Väärä merkki: '"+ruutu+"'. Hyväksytyt merkit: '#' ja '.'");
            }
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
        return (int) etaisyysAlusta;
    }
    
    public void setKasitelty() {
        kasitelty = true;
    }
    
    public boolean onkoKasitelty() {
        return kasitelty;
    }
    
    public int getKustannus() {
        return kustannus;
    }
    
    public Ruutu getEdellinen() {
        return edellinen;
    }
    
    public void setEdellinen(Ruutu edellinen) {
        this.edellinen = edellinen;
    }
 
}
