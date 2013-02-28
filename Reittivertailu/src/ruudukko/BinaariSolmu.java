/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class BinaariSolmu {
    private int sijaintiBinaariKeossa;
    
    public BinaariSolmu() {
        sijaintiBinaariKeossa = -1;
    }
    
    public void setSijaintiKeossa(int sijainti) {
        this.sijaintiBinaariKeossa = sijainti;
    }
    
    
    public int getSijaintiKeossa() {
        return sijaintiBinaariKeossa;
    }
}
