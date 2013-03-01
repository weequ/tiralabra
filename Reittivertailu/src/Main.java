
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.GUI;
import reittivertailu.Reittivertailu;
import ruudukko.Ruudukko;
import tiedostonLuku.Lukija;


/**
 * Luokka josta ohjelma lähtee käyntiin.
 * @author Antti
 */
public class Main {
    
    /**
     * Käynnistää ohjelman
     * @param args Ei merkitystä. (Voi olla mitä tahansa)
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].toLowerCase().contains("vertaa")) {
            kaynnistaVertailu();
        } else {
            //kaynnistaVertailu();
            kaynnistaKayttoliittyma();
        }
    }
    
    
    private static void kaynnistaVertailu() {
        //0% esteitä vertailu eri kokoisilla ruudukoilla
        Reittivertailu esteetonVertailu1 = new Reittivertailu(50, 50, 0.0);
        esteetonVertailu1.vertaa(10000);
        
        Reittivertailu esteetonVertailu2 = new Reittivertailu(100, 100, 0.0);
        esteetonVertailu2.vertaa(10000);
        
        Reittivertailu esteetonVertailu3 = new Reittivertailu(150, 150, 0.0);
        esteetonVertailu3.vertaa(10000);
        
        
        //15% esteitä vertailu eri kokoisilla ruudukoilla
        Reittivertailu vahanEsteitaVertailu1 = new Reittivertailu(50, 50, 0.15);
        vahanEsteitaVertailu1.vertaa(10000);
        
        Reittivertailu vahanEsteitaVertailu2 = new Reittivertailu(100, 100, 0.15);
        vahanEsteitaVertailu2.vertaa(10000);
        
        Reittivertailu vahanEsteitaVertailu3 = new Reittivertailu(150, 150, 0.15);
        vahanEsteitaVertailu3.vertaa(10000);
        
        
        //30% esteitä vertailu eri kokoisilla ruudukoilla
        Reittivertailu paljonEsteitaVertailu1 = new Reittivertailu(50, 50, 0.30);
        paljonEsteitaVertailu1.vertaa(10000);
        
        Reittivertailu paljonEsteitaVertailu2 = new Reittivertailu(100, 100, 0.30);
        paljonEsteitaVertailu2.vertaa(10000);
        
        Reittivertailu paljonEsteitaVertailu3 = new Reittivertailu(150, 150, 0.30);
        paljonEsteitaVertailu3.vertaa(10000);
    }
    
    private static void kaynnistaKayttoliittyma() {
        Lukija lukija = null;
        Ruudukko ruudukko;
        try {
            lukija = new Lukija("kartta.txt");
            ruudukko = new Ruudukko(lukija.getText());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Tiedostoa kartta.txt ei löydy ohjelman juuresta (jar tiedoston kanssa samasta kansiosta)");
            ruudukko = new Ruudukko(100, 100, 0.2);
        } catch (Exception ex) {
            ruudukko = new Ruudukko(100, 100, 0.2);
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
        }
        ruudukko.setLahto(0, 0);
        ruudukko.setMaali(ruudukko.getLeveys()-1, ruudukko.getKorkeus()-1);
        GUI gui = new GUI(ruudukko);
        gui.pack();
    }
}
