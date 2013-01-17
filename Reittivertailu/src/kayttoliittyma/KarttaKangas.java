/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import ruudukko.Ruudukko;

/**
 *
 * @author Antti
 */
public class KarttaKangas extends Canvas{
    
    private static final int RUUDUNKOKO = 10;
    private Ruudukko ruudukko;
    
    public KarttaKangas(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
        setRuudukko(ruudukko);
    }
    
    public KarttaKangas() {
        this.ruudukko = null;
    }
    
    public void setRuudukko(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
        Dimension koko = new Dimension(ruudukko.getLeveys()*RUUDUNKOKO, ruudukko.getKorkeus()*RUUDUNKOKO);
        //setSize(koko);
        setPreferredSize(koko);
        //setMinimumSize(koko);
        //setMaximumSize(koko);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ruudukko == null) return;
        g.setColor(Color.RED);
        for (int y = 0; y < ruudukko.getKorkeus(); y++) {
            for (int x = 0; x < ruudukko.getLeveys(); x++) {
                if (ruudukko.getRuutu(x, y).onkoEste()) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(x*RUUDUNKOKO, y*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
            }
        }
        
    }
}
