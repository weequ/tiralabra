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
        etaisyysAlusta = Double.POSITIVE_INFINITY;
        kasitelty = false;
        edellinen = null;
        this.ruudukko = ruudukko;
        this.x = x;
        this.y = y;
        if (ruutu >= '0' && ruutu <= '9') {
            este = false;
            kustannus = ruutu-'0';
        } else {
            switch (ruutu) {
                case '-': kustannus = -1;
                case '#':
                    este = true;
                    break;
                default:
                    throw new Exception("Väärä merkki: '"+ruutu+"'. Hyväksytyt merkit: '0'-'9', '-' ja '#'");
            }
       }
        
    }
    
    public boolean onkoEste() {
        return este;
    }
    
    public boolean onkoKasitelty() {
        return kasitelty;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getKustannus() {
        return kustannus;
    }
    
    public double getEtaisyysAlusta() {
        return (int) etaisyysAlusta;
    }
    
    public Ruutu getEdellinen() {
        return edellinen;
    }
    
    public Ruutu[] getNaapurit() {
        return new Ruutu[] {
            ruudukko.getRuutu(x-1, y),
            ruudukko.getRuutu(x+1, y),
            ruudukko.getRuutu(x, y-1),
            ruudukko.getRuutu(x, y+1)
        };
    }
    
    public void setKasitelty() {
        kasitelty = true;
    }
    
    public void setEtaisyysAlusta(double etaisyys) {
        this.etaisyysAlusta = etaisyys;
    }
    
    public void setEdellinen(Ruutu edellinen) {
        this.edellinen = edellinen;
    }
 
}
