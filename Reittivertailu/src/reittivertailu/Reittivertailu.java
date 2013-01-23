/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reittivertailu;

import algoritmit.LyhimmanPolunAlgoritmi;
import algoritmit.aTahti.ATahti;
import algoritmit.dijkstra.Dijkstra;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.GUI;
import ruudukko.Ruudukko;
import tiedostonLuku.Lukija;

/**
 * KESKENERÄINEN
 * @author Antti
 */
public class Reittivertailu {

    private Ruudukko ruudukko;
    
    public Reittivertailu() {
        Lukija lukija = null;
        try {
            lukija = new Lukija("testikartta.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        try {
            ruudukko = new Ruudukko(lukija.getText());
        } catch (Exception ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        ruudukko.setLahto(2, 2);
        ruudukko.setMaali(ruudukko.getLeveys()-1, ruudukko.getKorkeus()-1);
        GUI gui = new GUI(ruudukko);
        gui.pack();
        LyhimmanPolunAlgoritmi dijkstra = new ATahti(ruudukko);
        dijkstra.suorita(1000);
        System.out.println("valmis!");
    }
    public static void main(String[] args) {
        new Reittivertailu();
    }
}
