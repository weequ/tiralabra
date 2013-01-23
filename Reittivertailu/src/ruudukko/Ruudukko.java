/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Ruudukko on reitinhakualgoritmeille tarkoitettu koordinaatisto.
 * Jokaista koordinaattia vastaa yksi ruutu.
 * @see Ruutu
 * @author Antti
 */
public class Ruudukko {
    /**
     * Ruudukon sisältämät ruudut
     */
    private Ruutu[][] ruudukko;//ruudukko[y][x]
    
    /**
     * Ruutu josta reitin haku aloitetaan.
     */
    private Ruutu lahto;
    
    /**
     * Ruutu johon reitinhaun on tarkoitus päättyä.
     */
    private Ruutu maali;
    
    
    private ArrayList<ChangeListener> changeListeners;
    
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
        System.out.println(rivit.length+ " riviä.");
        ruudukko = new Ruutu[rivit.length][rivit[0].length()];
        
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
    
    //Loppu on piirtämistä varten. (Kesken)
    
    protected void lahetaMuutosTapahtuma(Object source) {
        ChangeEvent cE = new ChangeEvent(source);
        for (ChangeListener cL : changeListeners) {
            cL.stateChanged(cE);
        }
    }

    public void addChangeListener(ChangeListener changeListener) {
        changeListeners.add(changeListener);
    }
    
    
    public void removeChangeListener(ChangeListener changeListener) {
        changeListeners.remove(changeListener);
    }
    
}
