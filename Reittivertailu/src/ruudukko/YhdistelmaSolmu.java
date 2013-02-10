/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class YhdistelmaSolmu implements BinaariSolmu, FibonacciSolmu {

    private FibonacciSolmu vasen, oikea, lapsi, vanhempi;
    private boolean merkitty;
    private int lapsiSolmujenMaara;
    
    private int sijaintiBinaariKeossa;
    
    public YhdistelmaSolmu() {
        merkitty = false;
        sijaintiBinaariKeossa = -1;
        vasen = oikea = this;
        lapsi = vanhempi = null;
        lapsiSolmujenMaara = 0;
    }
    
    
    @Override
    public void setSijaintiKeossa(int sijainti) {
        sijaintiBinaariKeossa = sijainti;
    }

    @Override
    public int getSijaintiKeossa() {
        return sijaintiBinaariKeossa;
    }

    @Override
    public void setVasen(FibonacciSolmu fS) {
        vasen = fS;
    }

    @Override
    public void setOikea(FibonacciSolmu fS) {
        oikea = fS;
    }

    @Override
    public void setLapsi(FibonacciSolmu fS) {
        lapsi = fS;
    }

    @Override
    public void setVanhempi(FibonacciSolmu fS) {
        vanhempi = fS;
    }

    @Override
    public FibonacciSolmu getVasen() {
        return vasen;
    }

    @Override
    public FibonacciSolmu getOikea() {
        return oikea;
    }

    @Override
    public FibonacciSolmu getLapsi() {
        return lapsi;
    }

    @Override
    public FibonacciSolmu getVanhempi() {
        return vanhempi;
    }

    @Override
    public void setMerkitty(boolean merkitty) {
        this.merkitty = merkitty;
    }

    @Override
    public boolean getMerkitty() {
        return merkitty;
    }

    @Override
    public void setLapsiSolmujenMaara(int maara) {
        lapsiSolmujenMaara = maara;
    }

    @Override
    public int getLapsiSolmujenMaara() {
        return lapsiSolmujenMaara;
    }
    
}
