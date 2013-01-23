/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonLuku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Lukee tiedoston merkkijonoksi.
 * @author Antti
 */
public class Lukija {
    
    private File tiedosto;
    private String sisalto;
    
    /**
     * Luo uuden Lukija olion parametrina määritellylle tiedostolle.
     * @param tiedostoPolku Polku josta tekstitiedosto löytyy.
     * @throws FileNotFoundException Jos tiedostoa ei löydy.
     */
    public Lukija(String tiedostoPolku) throws FileNotFoundException {
        this.tiedosto = new File(tiedostoPolku);
        lue();
    }
    
    /**
     * Päivittää getText metodilla haettavan tekstin.
     * @throws FileNotFoundException 
     * @see #getText() 
     */
    public final void lue() throws FileNotFoundException {
        Scanner lukija = new Scanner(this.tiedosto);
        this.sisalto = "";
        if (lukija.hasNextLine()) {
            while(true) {
                this.sisalto += lukija.nextLine();
                if (!lukija.hasNextLine()) break;
                this.sisalto += System.getProperty("line.separator");
            }
        }
        lukija.close();
    }
    
    /**
     * Palauttaa viimeksi lue() metodilla luetun merkkijonon.
     * @return Viimeksi lue() metodilla luettu merkkijono.
     */
    public String getText() {
        return sisalto;
    }
}
