/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class BinaariRuudukko extends Ruudukko {
    
    
    public BinaariRuudukko(String ruudukkoString) throws Exception {
        super(ruudukkoString);
    }
    
    @Override
    protected void tulkkaaRivit(String[] rivit) throws Exception {
        for (int y = 0; y < rivit.length; y++) {
            if (rivit[y].length() != rivit[0].length()) {
                throw new Exception("Ruudukko sai syötteeksi eripituisia rivejä");
            }
            for (int x = 0; x < rivit[y].length(); x++) {
                this.ruudukko[y][x] = (Ruutu) new BinaariRuutu(this, rivit[y].charAt(x), x, y); //Tässä ero!
            }
        }
    }
}
