/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class BinaariRuutu extends Ruutu {
    public int sijainti;
    
    public BinaariRuutu(Ruudukko ruudukko, char ruutu, int x, int y) throws Exception  {
        super(ruudukko, ruutu, x, y);
        sijainti = 0;
    }
}
