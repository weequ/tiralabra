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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JComponent;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 * JComponent olion perivä kangas jonka tehtävänä on piirtää sille määriteltyä ruudukkoa.
 * @author Antti
 */
public class KarttaKangas extends JComponent{
    /**
     * Ruudun koko (korkeus ja leveys) pikseleinä
     */
    private static final int RUUDUNKOKO = 30;
    
    /**
     * Kankaalle piirrettävä ruudukko
     */
    private Ruudukko ruudukko;
    
    /**
     * Luo karttakankaan ruudukosta
     * @param ruudukko Ruudukko joka asetetaan piirrettäväksi
     */
    
    public KarttaKangas(Ruudukko ruudukko) {
        setRuudukko(ruudukko);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("aaa");
                repaint();
            }
        };

        new Timer(100, taskPerformer).start();
    }
    /**
     * Luo tyhjän karttakankaan
     */
    public KarttaKangas() {
        this(null);
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
        for (int y = 0; y < ruudukko.getKorkeus(); y++) {
            for (int x = 0; x < ruudukko.getLeveys(); x++) {
                Ruutu kasiteltavaRuutu = ruudukko.getRuutu(x, y);
                if (kasiteltavaRuutu.onkoEste()) {
                    g.setColor(Color.BLACK);
                } else if (kasiteltavaRuutu.onkoKasitelty()) {
                    g.setColor(Color.BLUE);
                } else {
                    int rgb = 255-kasiteltavaRuutu.getKustannus()*20;
                    Color c = new Color(rgb, rgb, rgb);
                    g.setColor(c);
                }
                g.fillRect(x*RUUDUNKOKO, y*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
            }
        }
        for (int y = 0; y < ruudukko.getKorkeus(); y++) {
            for (int x = 0; x < ruudukko.getLeveys(); x++) {
                Ruutu edellinen = ruudukko.getRuutu(x, y).getEdellinen();
                if (edellinen != null) {
                    g.setColor(Color.PINK);
                    g.fillOval(x*RUUDUNKOKO+RUUDUNKOKO/2-2, y*RUUDUNKOKO+RUUDUNKOKO/2-2, 4, 4);
                    g.drawLine(x*RUUDUNKOKO+RUUDUNKOKO/2, y*RUUDUNKOKO+RUUDUNKOKO/2, edellinen.getX()*RUUDUNKOKO+RUUDUNKOKO/2, edellinen.getY()*RUUDUNKOKO+RUUDUNKOKO/2);
                }
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(ruudukko.getLahto().getX()*RUUDUNKOKO, ruudukko.getLahto().getY()*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
        g.setColor(Color.RED);
        g.fillRect(ruudukko.getMaali().getX()*RUUDUNKOKO, ruudukko.getMaali().getY()*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
        
    }
    
    @Override
    public void repaint() {
        paint(this.getGraphics());
    }
}
