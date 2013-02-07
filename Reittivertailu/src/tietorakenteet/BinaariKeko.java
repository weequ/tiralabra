package tietorakenteet;

import ruudukko.BinaariRuutu;


/**
 * Minimikeon toteuttava binäärikeko.
 * @author Antti
 */
public class BinaariKeko {
    private DynaaminenTaulukko<BinaariRuutu> keko;
    private int koko;
    
    /**
     * Luo uuden binäärikeon.
     * @param aloitusKapasiteetti Kuinka isolla taulukolla aloitetaan.
     */
    public BinaariKeko(int aloitusKapasiteetti) {
        keko = new DynaaminenTaulukko<>(aloitusKapasiteetti);
        koko = 0;
    }
    
    
    /**
     * Lisää alkion kekoon.
     * @param alkio Alkio joka lisätään kekoon
     */
    public void lisaa(BinaariRuutu alkio) {
        asetaAlkio(koko, alkio);
        paivitaAvain(koko);
        koko++;
    }
    
    /**
     * Palauttaa keon pienimmän alkion poistamatta sitä.
     * @return Pienin alkio
     */
    public BinaariRuutu getPienin() {
        return keko.getAlkio(0);
    }
    
    /**
     * Poistaa ja palauttaa pienimmän alkion.
     * @return Poistettu alkio.
     */
    public BinaariRuutu poistaPienin() {
        BinaariRuutu pienin = keko.getAlkio(0);
        asetaAlkio(0, keko.getAlkio(koko-1));
        koko--;
        korjaaAlas(0);
        pienin.sijainti = -1;
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
        BinaariRuutu apumuuttuja = keko.getAlkio(alkio1);
        asetaAlkio(alkio1, keko.getAlkio(alkio2));
        asetaAlkio(alkio2, apumuuttuja);
    }
    
    private void korjaaAlas(int sijainti) {
        int pienin;
        if (oikeaLapsi(sijainti) <= koko) {
            if (keko.getAlkio(vasenLapsi(sijainti)).compareTo(keko.getAlkio(oikeaLapsi(sijainti))) < 0) {
                pienin = vasenLapsi(sijainti);
            } else {
                pienin = oikeaLapsi(sijainti);
            }
            if (keko.getAlkio(sijainti).compareTo(keko.getAlkio(pienin)) < 0) {
                vaihda(sijainti, pienin);
                korjaaAlas(pienin);
            }
        } else if (vasenLapsi(sijainti) == koko && keko.getAlkio(sijainti).compareTo(keko.getAlkio(vasenLapsi(sijainti))) < 0) {
            vaihda(sijainti, vasenLapsi(sijainti));
        }
    }
    
    
    private void korjaaYlos(int sijainti) {
        while(sijainti > 0 && keko.getAlkio(vanhempi(sijainti)).compareTo(keko.getAlkio(sijainti)) > 0) {
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
    private void asetaAlkio(int sijainti, BinaariRuutu alkio) {
        keko.setAlkio(sijainti, alkio);
        //keko[sijainti] = alkio;
        keko.getAlkio(sijainti).sijainti = sijainti;
    }
    
    
}
