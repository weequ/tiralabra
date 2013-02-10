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

/**
 *
 * @author Antti
 */
public class DynaaminenTaulukkoTest {
    
    private DynaaminenTaulukko<Integer> taulukko;
    
    
    public DynaaminenTaulukkoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        taulukko = new DynaaminenTaulukko<>(5);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLisaa() {
        assertEquals(taulukko.getKoko(), 0);
        taulukko.lisaa(5);
        assertEquals(taulukko.getKoko(), 1);
    }

    @Test
    public void testGetAlkio() {
        taulukko.lisaa(5);
        assertEquals(taulukko.getAlkio(0).intValue(), 5);
    }

    @Test
    public void testSetAlkio() {
        assertEquals(taulukko.getKoko(), 0);
        taulukko.lisaa(5);
        assertEquals(taulukko.getKoko(), 1);
        taulukko.setAlkio(0, 13);
        assertEquals(taulukko.getAlkio(0).intValue(), 13);
        assertEquals(taulukko.getKoko(), 1);
    }
    
    @Test
    public void testaaKasvaako() {
        for (int i = 0; i < 50; i++) {
            taulukko.lisaa(i);
        }
        for (int i = 0; i < 50; i++) {
            assertEquals(taulukko.getAlkio(i).intValue(), i);
        }
    }

    @Test
    public void testGetKoko() {
        for (int i = 0; i < 50; i++) {
            taulukko.lisaa(i);
        }
        assertEquals(taulukko.getKoko(), 50);
    }
    
    @Test
    public void testaaPoisto() {
        for (int i = 0; i < 50; i++) {
            taulukko.lisaa(i);
        }
        assertEquals(taulukko.poistaAlkio(35).intValue(), 35);
        assertEquals(taulukko.getKoko(), 49);
        assertEquals(taulukko.getAlkio(47).intValue(), 48);
    }
}
