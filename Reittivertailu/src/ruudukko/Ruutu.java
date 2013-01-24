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
    
    /**
     * Pysyykö ruudun läpi kulkemaan
     * @return 
     */
    public boolean onkoEste() {
        return este;
    }
    
    /**
     * Onko algoritmi jo käsitellyt ruudun
     * @return 
     */
    public boolean onkoKasitelty() {
        return kasitelty;
    }
    
    /**
     * Hakee X koordinaatin ruudukossa
     * @return X koordinaatti
     */
    public int getX() {
        return x;
    }
    
    /**
     * Hakee y koordinaatin ruudukossa
     * @return Y koordinaatti
     */
    public int getY() {
        return y;
    }
    
    /**
     * Hakee ruudun läpäisyn kustannuksen
     * @return Läpi kulkemiseen vaadittava kustannus
     */
    public int getKustannus() {
        return kustannus;
    }
    
    /**
     * Hakee etäisyyden alusta lyhyintä polkua pitkin. Voi muuttua jos ruutu ei ole vielä käsitelty.
     * @return Etäisyys alusta lyhyintä polkua pitkin
     */
    public double getEtaisyysAlusta() {
        return (int) etaisyysAlusta;
    }
    
    /**
     * Hakee ruudun josta tultiin tähän ruutuun
     * @return Ruutu josta tultiin tähän ruutuun
     */
    public Ruutu getEdellinen() {
        return edellinen;
    }
    
    /**
     * Hakee ruudun vierusruudut
     * @return Ruudun vierusruudut
     */
    public Ruutu[] getNaapurit() {
        return new Ruutu[] {
            ruudukko.getRuutu(x-1, y),
            ruudukko.getRuutu(x+1, y),
            ruudukko.getRuutu(x, y-1),
            ruudukko.getRuutu(x, y+1)
        };
    }
    
    /**
     * Asettaa ruudun käsitellyksi
     */
    public void setKasitelty() {
        kasitelty = true;
    }
    
    /**
     * Asettaa ruudulle etäisyyden alusta
     * @param etaisyys Etäisyys alusta
     */
    public void setEtaisyysAlusta(double etaisyys) {
        this.etaisyysAlusta = etaisyys;
    }
    
    /**
     * Asettaa ruudun josta tultiin tähän ruutuun
     * @param edellinen Ruutu josta tultiin tähän ruutuun
     */
    public void setEdellinen(Ruutu edellinen) {
        this.edellinen = edellinen;
    }
 
}
