package ruudukko;

import java.util.Iterator;

/**
 * Kaksisuuntainen verkko, jossa jokaista solmua vastaa yksi ruutu.
 * @see Ruutu
 * @author Antti
 */
public class Ruudukko implements Iterable<Ruutu> {
    /**
     * Ruudukon sisältämät ruudut
     */
    protected Ruutu[][] ruudukko;//ruudukko[y][x]
    
    /**
     * Ruutu josta reitin haku aloitetaan.
     */
    private Ruutu lahto;//Public jotta voidaan suoraan viitata tähän ruutuun keossa.
    
    /**
     * Ruutu johon reitinhaun on tarkoitus päättyä.
     */
    private Ruutu maali;
    
    
    /**
     * Luo ruudukon ja generoi esteet satunnaisesti parametrina annetulla todennäköisyydellä.
     * @param leveys Ruudukon leveys
     * @param korkeus Ruudukon korkeus
     * @param esteenTodennakoisyys Todennäköisyys jolla yksittäinen ruutu asetetaan esteeksi.
     */
    public Ruudukko(int leveys, int korkeus, double esteenTodennakoisyys) {
        ruudukko = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                ruudukko[y][x] = new Ruutu(this, x, y, esteenTodennakoisyys);
            }
        }
    }
    
    /**
     * Luo ruudukon rivinvaihdot sisältävästä merkkijonosta
     * @param ruudukkoString Rivinvaihdot sisältävä merkkijono.
     * @throws Exception Jos syötteenä on eripituisia rivejä tai ei hyväksyttyjä merkkejä.
     */
    public Ruudukko(String ruudukkoString) throws Exception {
        this(ruudukkoString.split(System.getProperty("line.separator")));
    }
    
    /**
     * Ruudukon alustus merkkijonoista
     * @param rivit Jokainen alkio kuvaa yhtä riviä.
     * @throws Exception Jos syötteenä on eripituisia rivejä tai ei hyväksyttyjä merkkejä.
     */
    public Ruudukko(String[] rivit) throws Exception {
        ruudukko = new Ruutu[rivit.length][rivit[0].length()];
        tulkkaaRivit(rivit);
    }
    
    
    /**
     * Arpoo esteen ruudukon jokaiseen ruutuun parametrina annetulla todennäköisyydellä.
     * @param esteenTodennakoisyys Todennäköisyys jolla yksittäinen ruutu asetetaan esteeksi.
     */
    public void arvoEsteet(double esteenTodennakoisyys) {
         for (Ruutu r : this) {
                r.arvoEste(esteenTodennakoisyys);
         }
    }
    
    protected void tulkkaaRivit(String[] rivit) throws Exception {
        for (int y = 0; y < rivit.length; y++) {
            if (rivit[y].length() != rivit[0].length()) {
                throw new Exception("Ruudukko sai syötteeksi eripituisia rivejä");
            }
            for (int x = 0; x < rivit[y].length(); x++) {
                this.ruudukko[y][x] = new Ruutu(this, rivit[y].charAt(x), x, y);
            }
        }
    }
    
    /**
     * Hakee ruudun kohdasta (x, y)
     * @param x ruudun y koordinaatti
     * @param y ruudun x koordinaatti
     * @return kohdassa (x, y) sijaitseva ruutu
     */
    public Ruutu getRuutu(int x, int y) {
        try {
            return this.ruudukko[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    /**
     * Hakee reitinhaun lahtoruudun
     * @return lahtoruutu
     */
    public Ruutu getLahto() {
        return lahto;
    }
    
    /**
     * Hakee reitinhaun maaliruudun
     * @return maaliruutu
     */
    public Ruutu getMaali() {
        return maali;
    }
    
    /**
     * Hakee ruudukon leveyden
     * @return ruudukon leveys
     */
    public int getLeveys() {
        return ruudukko[0].length;
    }
    
    /**
     * Hakee ruudukon korkeuden
     * @return ruudkon korkeus
     */
    public int getKorkeus() {
        return ruudukko.length;
    }
    
    /**
     * Asettaa ruudukolle uuden lahtoruudun
     * @param x lahtoruudun x koordinaatti
     * @param y lahtoruudun y koordinaatti
     */
    public void setLahto(int x, int y) {
        lahto = ruudukko[y][x];
        lahto.setEtaisyysAlusta(0);
    }
    
    /**
     * Arpoo lähtöruudun annettuun suorakaiteeseen kunnes se ei ole este.
     * @param x1 Vasen reuna
     * @param y1 Yläreuna
     * @param x2 Oikea reuna
     * @param y2 Alareuna
     * @return True jos tyhjä ruutu löytyi. False jos tyhjää ruutua ei löydy 1000 arvonnalla.
     */
    public boolean setLahtoTyhjaan(int x1, int y1, int x2, int y2) {
        int epaonnistumiset = 0;
        do {
            epaonnistumiset++;
            if (epaonnistumiset > 1000) {
                return false;
            }
            setLahto(x2-(int)(Math.random()*x1), y2-(int)(Math.random()*y1));
        } while (getLahto().onkoEste());
        return true;
    }
    
    /**
     * Asettaa ruudukolle uuden maaliruudun
     * @param x maaliruudun x koordinaatti
     * @param y maaliruudun y koordinaatti
     */
    public void setMaali(int x, int y) {
        maali = ruudukko[y][x];
    }
    
    /**
     * Arpoo maaliruudun annettuun suorakaiteeseen kunnes se ei ole este.
     * @param x1 Vasen reuna
     * @param y1 Yläreuna
     * @param x2 Oikea reuna
     * @param y2 Alareuna
     * @return True jos tyhjä ruutu löytyi. False jos tyhjää ruutua ei löydy 1000 arvonnalla.
     */
    public boolean setMaaliTyhjaan(int x1, int y1, int x2, int y2) {
        int epaonnistumiset = 0;
        do {
            epaonnistumiset++;
            if (epaonnistumiset > 1000) {
                return false;
            }
            setMaali(x2-(int)(Math.random()*x1), y2-(int)(Math.random()*y1));
        } while (getMaali().onkoEste());
        return true;
    }

    @Override
    public Iterator<Ruutu> iterator() {
        return new Iterator<Ruutu>() {
            int nextX = 0;
            int nextY = 0;
            @Override
            public boolean hasNext() {
                return (nextX < getLeveys() && nextY < getKorkeus());
            }

            @Override
            public Ruutu next() {
                Ruutu tulos = ruudukko[nextY][nextX];
                if (nextX < getLeveys()-1) {
                    nextX++;
                } else {
                    nextY++;
                    nextX = 0;
                }
                return tulos;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }
    
    
}
