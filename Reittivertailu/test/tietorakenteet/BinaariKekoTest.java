/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ruudukko.BinaariSolmu;

/**
 *
 * @author Antti
 */
public class BinaariKekoTest {
    
    class BinaariSolmuImpl implements BinaariSolmu {

        public int arvo;
        private int sijainti;
        
        public BinaariSolmuImpl(Integer arvo) {
            this.arvo = arvo;
            sijainti = -1;
        }
        
        @Override
        public void setSijaintiKeossa(int sijainti) {
            this.sijainti = sijainti;
        }

        @Override
        public int getSijaintiKeossa() {
            return sijainti;
        }
        
    }
    
    private BinaariKeko<BinaariSolmuImpl> binaariKeko;
    
    public BinaariKekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        binaariKeko = new BinaariKeko<>(50, new Comparator<BinaariSolmuImpl>() {

            @Override
            public int compare(BinaariSolmuImpl o1, BinaariSolmuImpl o2) {
                return o1.arvo-o2.arvo;
            }
        });
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetKokoLisaaJaPienin() {
        assertEquals(binaariKeko.getKoko(), 0);
        binaariKeko.lisaa(new BinaariSolmuImpl(5));
        assertEquals(binaariKeko.getKoko(), 1);
        binaariKeko.lisaa(new BinaariSolmuImpl(2));
        assertEquals(binaariKeko.getPienin().arvo, 2);
        assertEquals(binaariKeko.getKoko(), 2);
    }

    @Test
    public void testPoistaPienin() {
        binaariKeko.lisaa(new BinaariSolmuImpl(8));
        binaariKeko.lisaa(new BinaariSolmuImpl(2));
        BinaariSolmuImpl pienin = binaariKeko.poistaPienin();
        assertEquals(pienin.arvo, 2);
        assertEquals(binaariKeko.getKoko(), 1);
        assertEquals(binaariKeko.getPienin().arvo, 8);
    }

    @Test
    public void testPaivitaAvain() {
        BinaariSolmuImpl solmu = new BinaariSolmuImpl(8);
        binaariKeko.lisaa(solmu);
        binaariKeko.lisaa(new BinaariSolmuImpl(2));
        solmu.arvo = 1;
        assertEquals(solmu.getSijaintiKeossa(), 1);//Toisena
        binaariKeko.paivitaAvain(solmu.getSijaintiKeossa());
        assertEquals(solmu.getSijaintiKeossa(), 0);//Ensimmäisenä (pienin)
        assertEquals(binaariKeko.getPienin().arvo, 1);
    }
}
