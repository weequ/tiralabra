/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 * BinääriSolmu on binäärikekoon kelpaava solmu. Binäärisolmuun on tallennettu sen sijainti binäärikeossa.
 * Älä käytä binäärisolmua useammassa kuin yhdessä binäärikeossa kerrallaan!
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
