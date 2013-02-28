
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
