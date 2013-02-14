/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reittivertailu;

import algoritmit.BellmanFord;
import algoritmit.AhneLyhimmanPolunAlgoritmi;
import algoritmit.aTahti.ATahti;
import algoritmit.dijkstra.Dijkstra;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.GUI;
import ruudukko.Ruudukko;
import tiedostonLuku.Lukija;

/**
 * Luokka josta kaikki lähtee liikkeelle. 
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
            //ruudukko = new Ruudukko(lukija.getText());
            ruudukko = new Ruudukko(100, 100, 0.2);
        } catch (Exception ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        do {
            ruudukko.setLahto(7-(int)(Math.random()*6), 7-(int)(Math.random()*6));
        } while (ruudukko.getLahto().onkoEste());
        do {
            //System.out.println("asetetaan maalia");
            ruudukko.setMaali(ruudukko.getLeveys()-10+(int)(Math.random()*6), ruudukko.getKorkeus()-10+(int)(Math.random()*6));
        } while(ruudukko.getMaali().onkoEste());
        GUI gui = new GUI(ruudukko);
        gui.pack();
        //LyhimmanPolunAlgoritmi dijkstra = new ATahti(ruudukko);
        //LyhimmanPolunAlgoritmi dijkstra = new Dijkstra(ruudukko);
        //dijkstra.suorita(100);
        BellmanFord bF = new BellmanFord(ruudukko);
        AhneLyhimmanPolunAlgoritmi dijkstra = new ATahti(ruudukko);
        dijkstra.suorita(50);
        //bF.suorita(0);
        System.out.println("valmis");
    }
    
    public void kaynnistaVertailu() {
        
    }
    
}
