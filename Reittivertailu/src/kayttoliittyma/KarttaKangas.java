/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.Timer;
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
    private static final int RUUDUNKOKO = 8;
    
    /**
     * Kankaalle piirrettävä ruudukko
     */
    private Ruudukko ruudukko;
    
    /**
     * Luo karttakankaan ruudukosta
     * @param ruudukko Ruudukko joka asetetaan piirrettäväksi
     */
    
    public KarttaKangas(final Ruudukko ruudukko) {
        setRuudukko(ruudukko);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("aaa");
                repaint();
            }
        };

        new Timer(100, taskPerformer).start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()/RUUDUNKOKO;
                int y = e.getY()/RUUDUNKOKO;
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1://Vasen
                        ruudukko.setLahto(x, y);
                        break;
                    case MouseEvent.BUTTON3://Oikea
                        ruudukko.setMaali(x, y);
                        break;
                }
                ((GUI)getFocusCycleRootAncestor()).getAlgoritmi().alusta();
            }
        
        });
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
        repaint();
    }
    
    public Ruudukko getRuudukko() {
        return ruudukko;
    }
    
    
    private void piirraRuudunTila(Graphics g, Ruutu ruutu) {
        if (ruutu.onkoEste()) {
            g.setColor(Color.BLACK);
        } else if (ruutu.getVaihe() == Ruutu.Vaihe.KASITELTY) {
            g.setColor(Color.BLUE);
        } else if (ruutu.getVaihe() == Ruutu.Vaihe.KASITTELYSSA) {
            g.setColor(Color.MAGENTA);
        } else {
            int rgb = 255-ruutu.getKustannus()*20;
            Color c = new Color(rgb, rgb, rgb);
            g.setColor(c);
        }
        g.fillRect(ruutu.getX()*RUUDUNKOKO, ruutu.getY()*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
    }
    
    private void piirraLahtoJaMaali(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(ruudukko.getLahto().getX()*RUUDUNKOKO, ruudukko.getLahto().getY()*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
        g.setColor(Color.RED);
        g.fillRect(ruudukko.getMaali().getX()*RUUDUNKOKO, ruudukko.getMaali().getY()*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
    }
    
    private void piirraYhteysEdelliseen(Graphics g, Ruutu ruutu) {
        if (ruutu == null) return;
        Ruutu edellinen = ruutu.getEdellinen();
        if (edellinen == null) return;
        g.setColor(Color.PINK);
        //g.fillOval(ruutu.getX()*RUUDUNKOKO+RUUDUNKOKO/2-2, ruutu.getY()*RUUDUNKOKO+RUUDUNKOKO/2-2, 4, 4);
        g.drawLine(edellinen.getX()*RUUDUNKOKO+RUUDUNKOKO/2, edellinen.getY()*RUUDUNKOKO+RUUDUNKOKO/2, ruutu.getX()*RUUDUNKOKO+RUUDUNKOKO/2, ruutu.getY()*RUUDUNKOKO+RUUDUNKOKO/2);
    }
    
    
    private void piirraRuutujenTilat(Graphics g) {
        for (Ruutu ruutu : ruudukko) {
            piirraRuudunTila(g, ruutu);
        }
    }
    
    private void piirraYhteydetEdellisiin(Graphics g) {
        for (Ruutu ruutu : ruudukko) {
            piirraYhteysEdelliseen(g, ruutu);
        }
    }
    
    private void piirraLyhyinReitti(Graphics g) {
        g.setColor(Color.ORANGE);
        Ruutu ruutu = ruudukko.getMaali();
        while(ruutu != null) {
            g.fillRect(ruutu.getX()*RUUDUNKOKO, ruutu.getY()*RUUDUNKOKO, RUUDUNKOKO, RUUDUNKOKO);
            ruutu = ruutu.getEdellinen();
        }
    }
    
    /**
     * Piirtää ruudukon
     * @param g Piirtämiseen käytetty Graphics olio.
     */
    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        if (ruudukko == null) return;
        piirraRuutujenTilat(g);
        piirraLyhyinReitti(g);
        piirraYhteydetEdellisiin(g);
        piirraLahtoJaMaali(g);
    }
    
}
