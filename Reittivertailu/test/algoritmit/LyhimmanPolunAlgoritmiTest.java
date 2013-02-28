/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit;

import algoritmit.aTahti.ATahti;
import algoritmit.dijkstra.Dijkstra;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ruudukko.Ruudukko;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class LyhimmanPolunAlgoritmiTest {
    
    LyhimmanPolunAlgoritmi lPA;
    ATahti aTahti;
    Dijkstra dijkstra;
    BellmanFord bellmanFord;
    Ruudukko ruudukko;
    
    public LyhimmanPolunAlgoritmiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ruudukko = new Ruudukko(50, 50, 0.2);
        ruudukko.setLahtoTyhjaan(2, 2, 5, 5);
        ruudukko.setMaaliTyhjaan(45, 45, 48, 48);
        aTahti = new ATahti(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI);
        dijkstra = new Dijkstra(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI);
        bellmanFord = new BellmanFord(ruudukko);
    }
    
    @After
    public void tearDown() {
        
    }

    
    @Test
    public void testaaYhtaPitkienReittienLoytyminen() {
        for (int i = 0; i < 10; i++) {
            aTahti.alusta();
            aTahti.suorita();
            double pituus1 = ruudukko.getMaali().getEtaisyysAlusta();
            dijkstra.alusta();
            dijkstra.suorita();
            double pituus2 = ruudukko.getMaali().getEtaisyysAlusta();
            bellmanFord.alusta();
            bellmanFord.suorita();
            double pituus3 = ruudukko.getMaali().getEtaisyysAlusta();
            assertEquals("Dijkstralla ja A* eripituinen reitti."+
                    "Dijkstra: "+pituus1+". A*: "+pituus2, pituus1, pituus2, 0.0001);
            assertEquals("A* ja BellmanFord eripituinen reitti."
                    + "A*:"+pituus2+". BellmanFord:"+pituus3, pituus2, pituus3, 0.0001);
        }
    }
}
