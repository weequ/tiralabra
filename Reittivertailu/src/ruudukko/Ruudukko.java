/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ruudukko;

/**
 *
 * @author Antti
 */
public class Ruudukko {
    
    private Ruutu[][] ruudukko;//ruudukko[y][x]
    private Ruutu lahto, maali;
    
    public Ruudukko(String ruudukkoString) throws Exception {
        this(ruudukkoString.split(System.getProperty("line.separator")));
    }
    
    public Ruudukko(String[] rivit) throws Exception {
        System.out.println(rivit.length+ " riviä.");
        ruudukko = new Ruutu[rivit.length][rivit[0].length()];
        
        for (int y = 0; y < rivit.length; y++) {
            if (rivit[y].length() != rivit[0].length()) {
                throw new Exception("Ruudukko sai syötteeksi eripituisia rivejä");
            }
            for (int x = 0; x < rivit[y].length(); x++) {
                this.ruudukko[y][x] = new Ruutu(rivit[y].charAt(x), x, y);
            }
        }
    }
    
    public Ruutu getRuutu(int x, int y) {
        return this.ruudukko[y][x];
    }
    
    public int getLeveys() {
        return ruudukko[0].length;
    }
    
    public int getKorkeus() {
        return ruudukko.length;
    }
    
    public Ruutu getLahto() {
        return lahto;
    }
    
    public Ruutu getMaali() {
        return maali;
    }
    
    public void setLahto(int x, int y) {
        lahto = ruudukko[y][x];
    }
    
    public void setMaali(int x, int y) {
        maali = ruudukko[y][x];
    }
    
    
}
