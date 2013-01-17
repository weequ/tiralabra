/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonLuku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Antti
 */
public class Lukija {
    
    private File tiedosto;
    private String sisalto;
    
    public Lukija(String tiedostoPolku) throws FileNotFoundException {
        this.tiedosto = new File(tiedostoPolku);
        lue();
    }
    
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
    
    
    public String getText() {
        return sisalto;
    }
}
