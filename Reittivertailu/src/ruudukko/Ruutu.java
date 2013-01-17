/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class Ruutu {
    
    private boolean este;
    private boolean kayty;
    private int x;
    private int y;
        
    public Ruutu(char ruutu, int x, int y) throws Exception {
        kayty = false;
        switch (ruutu) {
            case '#':
                este = true;
                break;
            case '.':
                este = false;
                break;
            default:
                throw new Exception("Väärä merkki: '"+ruutu+"'. Hyväksytyt merkit: '#' ja '.'");
        }
        
    }
    
    public boolean onkoEste() {
        return este;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
}
