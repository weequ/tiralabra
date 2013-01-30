/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reittivertailu.Reittivertailu;
import tiedostonLuku.Lukija;

/**
 *
 * @author Antti
 */
public class RuutuTest {
    
   private Ruudukko ruudukko;
        
    public RuutuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            ruudukko = new Ruudukko(
                    new String[] {
                        "3#111",
                        "12111",
                        "11111",
                        "11111",
                        "11114"
                    });
        } catch (Exception ex) {
            Logger.getLogger(Reittivertailu.class.getName()).log(Level.SEVERE, null, ex);
            fail("ruudukon luominen epäonnistui");
        }
        ruudukko.setLahto(0, 0);
        ruudukko.setMaali(ruudukko.getLeveys()-1, ruudukko.getKorkeus()-1);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testOnkoEste() {
        Ruutu este = ruudukko.getRuutu(1, 0);
        Ruutu eiEste = ruudukko.getRuutu(0, 0);
        assertEquals(este.onkoEste(), true);
        assertEquals(eiEste.onkoEste(), false);
    }

//    @Test
//    public void testOnkoSetKasitelty() {
//        Ruutu ruutu = ruudukko.getRuutu(0, 0);
//        assertEquals(ruutu.onkoKasitelty(), false);
//        ruutu.setKasitelty();
//        assertEquals(ruutu.onkoKasitelty(), true);
//    }

    @Test
    public void testGetX() {
        Ruutu r = ruudukko.getRuutu(4, 0);
        assertEquals(r.getX(), 4);
    }

    @Test
    public void testGetY() {
        Ruutu r = ruudukko.getRuutu(0, 4);
        assertEquals(r.getY(), 4);
    }

    @Test
    public void testGetKustannus() {
        Ruutu r = ruudukko.getRuutu(0, 0);
        assertEquals(r.getKustannus(), 3);
    }

    @Test
    public void testGetSetEtaisyysAlusta() {
        Ruutu r = ruudukko.getRuutu(0, 0);
        assertEquals(r.getEtaisyysAlusta(), 0, 0.01);
        r.setEtaisyysAlusta(4);
        assertEquals(r.getEtaisyysAlusta(), 4, 0.01);
    }

    @Test
    public void testGetSetEdellinen() {
        Ruutu r = ruudukko.getRuutu(0, 0);
        r.setEdellinen(null);
        assertEquals(r.getEdellinen(), null);
        r.setEdellinen(ruudukko.getRuutu(0, 1));
        assertEquals(r.getEdellinen(), ruudukko.getRuutu(0, 1));
    }

    @Test
    public void testGetNaapurit() {
        Ruutu r = ruudukko.getRuutu(0, 0);
        Ruutu[] tulos = new Ruutu[] {
            ruudukko.getRuutu(-1, 0),
            ruudukko.getRuutu(+1, 0),
            ruudukko.getRuutu(0, -1),
            ruudukko.getRuutu(0, +1)
        };
        assertArrayEquals(r.getNaapurit(), tulos);
    }
}
