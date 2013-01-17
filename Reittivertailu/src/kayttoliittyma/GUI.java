/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import reittivertailu.Reittivertailu;
import ruudukko.Ruudukko;

/**
 *
 * @author Antti
 */
public class GUI extends JFrame {
    
    Reittivertailu reittivertailu;
    
    public GUI(Ruudukko ruudukko) {
        setTitle("");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JScrollPane ikkuna = new JScrollPane(new KarttaKangas(ruudukko));
        add(ikkuna);
    }
    
    
}
