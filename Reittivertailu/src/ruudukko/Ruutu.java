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
public class Ruutu extends YhdistelmaSolmu {

    private Ruudukko ruudukko;
    private boolean este;
    private Vaihe vaihe;
    private int kustannus;
    private int x;
    private int y;
    private double etaisyysAlusta;
    private Ruutu edellinen;
    
    /**
     * Kuvaa ruudun tilaa algoritmissa.
     * Määrittelee kolme vaihetta: KASITELTY, KASITTELYSSA ja KASITTELEMATON
     */
    public enum Vaihe {
        KASITELTY, KASITTELYSSA, KASITTELEMATON 
    }
        
    /**
     * Luo uuden ruudun.
     * @param ruudukko Ruudukko johon tämä ruutu kuuluu.
     * @param ruutu Kustannus kulkea ruudussa. '#' = Este(äärretön), '-' = -1, '0'..'9'-> 0..9
     * @param x Ruudun x koordinaatti.
     * @param y Ruudun y koordinaatti.
     * @throws Exception Jos parametri ruutu ei kuulu joukkoon {'#', '-', '0', .. ,'9'}
     */
    public Ruutu(Ruudukko ruudukko, char ruutu, int x, int y) throws Exception {
        alustaMuuttujat();
        this.ruudukko = ruudukko;
        this.x = x;
        this.y = y;
        tulkitseMerkki(ruutu);
    }

    private void alustaMuuttujat() {
        vaihe = Vaihe.KASITTELEMATON;
        etaisyysAlusta = Double.POSITIVE_INFINITY;
        edellinen = null;
    }
    
    private void tulkitseMerkki(char ruutu) throws Exception {
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
    public Vaihe getVaihe() {
        return vaihe;
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
            ruudukko.getRuutu(x, y-1),
            ruudukko.getRuutu(x+1, y),
            ruudukko.getRuutu(x, y+1)/*,
            ruudukko.getRuutu(x-1, y-1),
            ruudukko.getRuutu(x+1, y+1),
            ruudukko.getRuutu(x-1, y+1),
            ruudukko.getRuutu(x+1, y-1)'*/
        };
    }
    
    /**
     * Asettaa ruudulle vaiheen
     * @param vaihe KASITTELEMATON, KASITTELYSSA tai KASITELTY
     */
    public void setVaihe(Vaihe vaihe) {
        this.vaihe = vaihe;
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
