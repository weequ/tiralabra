package tietorakenteet;

import java.util.Comparator;
import ruudukko.BinaariSolmu;


/**
 * Minimikeon toteuttava binäärikeko.
 * @author Antti
 */
public class BinaariKeko<T extends BinaariSolmu> {
    private DynaaminenTaulukko<T> keko;
    private int koko;
    private Comparator<? super T> vertailija;
    /**
     * Luo uuden binäärikeon.
     * @param aloitusKapasiteetti Kuinka isolla taulukolla aloitetaan.
     */
    public BinaariKeko(int aloitusKapasiteetti, Comparator<? super T> vertailija) {
        this.vertailija = vertailija;
        keko = new DynaaminenTaulukko<>(aloitusKapasiteetti);
        koko = 0;
    }
    
    public int getKoko() {
        return koko;
    }
    
    
    /**
     * Lisää alkion kekoon.
     * @param alkio Alkio joka lisätään kekoon
     */
    public void lisaa(T alkio) {
        System.out.println("lisaa (binaarikeko) "+koko+", "+keko.getKoko());
        keko.lisaa(alkio);
        alkio.setSijaintiKeossa(koko);
        korjaaYlos(koko);
        koko++;
    }
    
    /**
     * Palauttaa keon pienimmän alkion poistamatta sitä.
     * @return Pienin alkio
     */
    public T getPienin() {
        return keko.getAlkio(0);
    }
    
    /**
     * Poistaa ja palauttaa pienimmän alkion.
     * @return Poistettu alkio.
     */
    public T poistaPienin() {
        T pienin = keko.getAlkio(0);
        asetaAlkio(0, keko.poistaAlkio(koko-1));
        koko--;
        korjaaAlas(0);
        pienin.setSijaintiKeossa(-1);
        return pienin;
    }
    
    
    /**
     * Siirtää alkion oikeaan paikkaan jos sitä on pienennetty. Alkion suurentaminen voi johtaa virheisiin.
     * @param sijainti Pienennetyn alkion sijainti kekotaulukossa
     */
    public void paivitaAvain(int sijainti) {
        korjaaYlos(sijainti);
    }
    
    
    //APUMETODIT:
    private int vasenLapsi(int i) {
        return 2*i+1;
    }
    
    private int oikeaLapsi(int i) {
        return 2*i+2;
    }
    
    private int vanhempi(int i) {
        return (i-1)/2;
    }
    
    private void vaihda(int alkio1, int alkio2) {
        T apumuuttuja = keko.getAlkio(alkio1);
        asetaAlkio(alkio1, keko.getAlkio(alkio2));
        asetaAlkio(alkio2, apumuuttuja);
    }
    
    private void korjaaAlas(int sijainti) {
        int pienin;
        if (oikeaLapsi(sijainti) <= koko) {
            if (vertailija.compare(keko.getAlkio(vasenLapsi(sijainti)), keko.getAlkio(oikeaLapsi(sijainti))) < 0) {
                pienin = vasenLapsi(sijainti);
            } else {
                pienin = oikeaLapsi(sijainti);
            }
            if (vertailija.compare(keko.getAlkio(sijainti), keko.getAlkio(pienin)) > 0) {
                vaihda(sijainti, pienin);
                korjaaAlas(pienin);
            }
        } else if (vasenLapsi(sijainti) == koko && vertailija.compare(keko.getAlkio(sijainti),keko.getAlkio(vasenLapsi(sijainti))) < 0) {
            vaihda(sijainti, vasenLapsi(sijainti));
        }
    }
    
    
    private void korjaaYlos(int sijainti) {
        while(sijainti > 0 && vertailija.compare(keko.getAlkio(vanhempi(sijainti)), keko.getAlkio(sijainti)) > 0) {
            //asetaAlkio(sijainti, keko[vanhempi(sijainti)]);
            vaihda(sijainti, vanhempi(sijainti));
            sijainti = vanhempi(sijainti);
        }
    }
    
    /**
     * Apumetodi joka asettaa alkion sijaintiin ja päivittää alkion sijainnin.
     * @param sijainti sijainti johon alkio asetetaan.
     * @param alkio alkio joka asetetaan sijaintiin.
     */
    private void asetaAlkio(int sijainti, T alkio) {
        keko.setAlkio(sijainti, alkio);
        //keko[sijainti] = alkio;
        keko.getAlkio(sijainti).setSijaintiKeossa(sijainti);
    }
    
    
}
