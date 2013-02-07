/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

/**
 * Taulukko joka kasvattaa koko kasvaa jos se menee täyteen.
 * @author Antti
 */
public class DynaaminenTaulukko<E> {
    private E[] alkiot;
    private int koko;
    
    public DynaaminenTaulukko(int aloitusKapasiteetti) {
        alkiot = (E[])new Object[aloitusKapasiteetti];
        koko = 0;
    }

    /**
     * Lisää alkion paikalle getKoko()
     * @param alkio
     */
    public void lisaa(E alkio) {
        if (alkiot.length == koko) {
            kaksinKertaistaKoko();
        }
        alkiot[koko] = alkio;
    }
    
    /**
     * Apumetodi joka kaksinkertaistaa taulukon koon.
     */
    private void kaksinKertaistaKoko() {
        E[] aputaulukko = (E[])new Object[alkiot.length*2];
        for (int i = 0; i < alkiot.length; i++) {
            aputaulukko[i] = alkiot[i];
        }
        alkiot = aputaulukko;
    }
    
    /**
     * Palauttaa alkion taulukon kohdasta sijainti.
     * @param sijainti Indeksi josta alkio palautetaan
     * @return 
     */
    public E getAlkio(int sijainti) {
        return alkiot[sijainti];
    }
    
    /**
     * Korvaa sijainnissa olevan alkion parametrina annetulla alkiolla.
     * @param sijainti Indeksi josta alkio korvataan
     * @param alkio Alkio joka asetetaan sijaintiin.
     */
    public void setAlkio(int sijainti, E alkio) {
        alkiot[sijainti] = alkio;
    }
    
    /**
     * Palauttaa kuinka monta alkiota taulukko sisältää.
     * @return Kuinka monta alkiota taulukko sisältää.
     */
    public int getKoko() {
        return koko;
    }
}
