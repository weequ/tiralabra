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
    private Ruutu lahto;
    
    /**
     * Ruutu johon reitinhaun on tarkoitus päättyä.
     */
    private Ruutu maali;
    
    
    public Ruudukko(int leveys, int korkeus, double esteenTodennakoisyys) {
        ruudukko = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                ruudukko[y][x] = new Ruutu(this, x, y, esteenTodennakoisyys);
            }    
        }
    }
    
    /**
     * Ruudukon alustus rivinvaihdot sisältävästä merkkijonosta
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
        if (lahto != null) {
            lahto.setEtaisyysAlusta(Double.POSITIVE_INFINITY);
        }
        lahto = ruudukko[y][x];
        lahto.setEtaisyysAlusta(0);
    }
    
    /**
     * Asettaa ruudukolle uuden maaliruudun
     * @param x maaliruudun x koordinaatti
     * @param y maaliruudun y koordinaatti
     */
    public void setMaali(int x, int y) {
        maali = ruudukko[y][x];
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
