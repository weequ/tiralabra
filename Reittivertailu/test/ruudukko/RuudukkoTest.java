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
public class RuudukkoTest {
    
    private Ruudukko ruudukko;
    
    public RuudukkoTest() {
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
                        "31111",
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
    public void testGetRuutu() {
        Ruutu r = ruudukko.getRuutu(1, 1);
        assertEquals(r.getKustannus(), 2);
        assertEquals(r.getX(), 1);
        assertEquals(r.getY(), 1);
    }

    @Test
    public void testGetLahto() {
        Ruutu r = ruudukko.getLahto();
        assertEquals(r.getKustannus(), 3);
        assertEquals(r.getX(), 0);
        assertEquals(r.getY(), 0);
    }

    @Test
    public void testGetMaali() {
        Ruutu r = ruudukko.getMaali();
        assertEquals(r.getKustannus(), 4);
        assertEquals(r.getX(), 4);
        assertEquals(r.getY(), 4);
    }

    @Test
    public void testGetLeveys() {
        assertEquals(ruudukko.getLeveys(), 5);
    }

    @Test
    public void testGetKorkeus() {
        assertEquals(ruudukko.getKorkeus(), 5);
    }

    @Test
    public void testSetLahto() {
        ruudukko.setLahto(1, 1);
        Ruutu r = ruudukko.getLahto();
        assertEquals(r.getKustannus(), 2);
        assertEquals(r.getX(), 1);
        assertEquals(r.getY(), 1);
    }

    @Test
    public void testSetMaali() {
        ruudukko.setMaali(1, 1);
        Ruutu r = ruudukko.getMaali();
        assertEquals(r.getKustannus(), 2);
        assertEquals(r.getX(), 1);
        assertEquals(r.getY(), 1);
    }
}
