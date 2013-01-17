/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reittivertailu;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.GUI;
import ruudukko.Ruudukko;
import tiedostonLuku.Lukija;

/**
 *
 * @author Antti
 */
public class Reittivertailu {

    public Reittivertailu() {
        Lukija lukija = null;
        try {
            lukija = new Lukija("testikartta.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        try {
            GUI gui = new GUI(new Ruudukko(lukija.getText()));
            gui.pack();
        } catch (Exception ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new Reittivertailu();
    }
}
