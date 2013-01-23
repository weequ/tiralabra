/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Paint;
import javax.swing.JComponent;
import ruudukko.Ruudukko;

/**
 * JComponent olion perivä kangas jonka tehtävänä on piirtää sille määriteltyä ruudukkoa.
 * @author Antti
 */
public class KarttaKangas extends JComponent{
    /**
     * Ruudun koko (korkeus ja leveys) pikseleinä
     */
    private static final int RUUDUNKOKO = 10;
    
    /**
     * Kankaalle piirrettävä ruudukko
     */
    private Ruudukko ruudukko;
    
    /**
     * Luo karttakankaan ruudukosta
     * @param ruudukko Ruudukko joka asetetaan piirrettäväksi
     */
    
    public KarttaKangas(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
        setRuudukko(ruudukko);
    }
    /**
     * Luo tyhjän karttakankaan
     */
    public KarttaKangas() {
        this.ruudukko = null;
        repaint(50);
    }
    
    /**
     * Asettaa uuden ruudukon piirrettäväksi
     * @param ruudukko 
     */
    public void setRuudukko(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
        Dimension koko = new Dimension(ruudukko.getLeveys()*RUUDUNKOKO, ruudukko.getKorkeus()*RUUDUNKOKO);
        setPreferredSize(koko);
    }
    
    
    /**
     * Piirtää ruudukon
     * @param g Piirtämiseen käytetty Graphics olio.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ruudukko == null) return;
        g.setColor(Color.RED);
        for (int y = 0; y < ruudukko.getKorkeus(); y++) {
            for (int x = 0; x < ruudukko.getLeveys(); x++) {
                if (ruudukko.getRuutu(x, y).onkoEste()) {
                    g.setColor(Color.RED);
                } else if (ruudukko.getRuutu(x, y).onkoKasitelty()) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(x*RUUDUNKOKO, y*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
            }
        }
        
    }
    
    @Override
    public void repaint() {
        paint(this.getGraphics());
    }
}
