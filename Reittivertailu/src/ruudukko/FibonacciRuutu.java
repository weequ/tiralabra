/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class FibonacciRuutu extends Ruutu {
    int lapsiSolmujenLukumaara;//'aste'
    public boolean merkitty;
    public FibonacciRuutu vanhempi;
    public FibonacciRuutu lapsi;
    public FibonacciRuutu vasen;
    public FibonacciRuutu oikea;
    public int lapsiSolmujenMaara;
    
    public FibonacciRuutu(Ruudukko ruudukko, char ruutu, int x, int y) throws Exception  {
        super(ruudukko, ruutu, x, y);
        vasen = oikea = this;
        lapsiSolmujenMaara = 0;
    }
}
