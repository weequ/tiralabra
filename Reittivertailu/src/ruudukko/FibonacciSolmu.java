/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public interface FibonacciSolmu {
    public void setVasen(FibonacciSolmu fS);
    public void setOikea(FibonacciSolmu fS);
    public void setLapsi(FibonacciSolmu fS);
    public void setVanhempi(FibonacciSolmu fS);
    public void setMerkitty(boolean merkitty);
    public FibonacciSolmu getVasen();
    public FibonacciSolmu getOikea();
    public FibonacciSolmu getLapsi();
    public FibonacciSolmu getVanhempi();
    public boolean getMerkitty();
    public void setLapsiSolmujenMaara(int maara);
    public int getLapsiSolmujenMaara();
}
