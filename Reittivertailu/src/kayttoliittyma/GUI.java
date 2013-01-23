/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import reittivertailu.Reittivertailu;
import ruudukko.Ruudukko;

/**
 * Keskeneräinen!!!
 * @author Antti
 */
public class GUI extends JFrame {
    
    Reittivertailu reittivertailu;
    
    public GUI(Ruudukko ruudukko) {
        setTitle("");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        final KarttaKangas k = new KarttaKangas(ruudukko);
        JPanel paneeli = new JPanel();
        JScrollPane ikkuna = new JScrollPane(paneeli);
        paneeli.add(k);
        add(ikkuna);
        paneeli.setAutoscrolls(false);
        paneeli.setBackground(Color.WHITE);
    }
    
    
    
}
