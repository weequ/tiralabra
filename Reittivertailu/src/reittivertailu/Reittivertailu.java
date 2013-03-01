package reittivertailu;

import algoritmit.AhneLyhimmanPolunAlgoritmi;
import algoritmit.BellmanFord;
import algoritmit.LyhimmanPolunAlgoritmi;
import algoritmit.aTahti.ATahti;
import algoritmit.dijkstra.Dijkstra;
import ruudukko.Ruudukko;

/**
 * Luokka jonka tehtävänä on vertailla Dijkstran, A* ja Bellman-Ford algoritmien suorituskykyä.
 * @author Antti
 */
public class Reittivertailu {

    private Ruudukko ruudukko;
    private LyhimmanPolunAlgoritmi[] algoritmit;
    int ruudukonLeveys, ruudukonKorkeus;
    double esteenTn;
    
    /**
     * Luo reittivertailu olion annetuilla parametreilla.
     * @param ruudukonLeveys Minkä leveyisessä ruudukossa vertaillaan algoritmeja?
     * @param ruudukonKorkeus Minkä korkeuisessa ruudukossa vertaillaan algoritmeja?
     * @param esteenTn Todennäköisyys jolla yksittäinen ruutu asetetaan esteeksi.
     */
    public Reittivertailu(int ruudukonLeveys, int ruudukonKorkeus, double esteenTn) {
        if (ruudukonLeveys < 20 || ruudukonKorkeus < 20) {
            throw new IllegalArgumentException("Ruudukon oltava kooltaan vähintään 20X20.");
        }
        this.ruudukonLeveys = ruudukonLeveys;
        this.ruudukonKorkeus = ruudukonKorkeus;
        this.esteenTn = esteenTn;
        this.ruudukko = new Ruudukko(ruudukonLeveys, ruudukonKorkeus, esteenTn);
        this.algoritmit = new LyhimmanPolunAlgoritmi[] {new Dijkstra(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI),
                new ATahti(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI),
                new BellmanFord(ruudukko)};
    }
    
    /**
     * Arpoo esteet ruudukkoon ja asettaa lähtö- ja maaliruudun esteettömään ruutuun. 
     * Jos tyhjää lähtö tai maaliruutua ei löydy kulmien läheisyydestä niin ruudukko arvotaan uudelleen.
     */
    private void arvoRuudukko() {
        int leveys = ruudukko.getLeveys();
        int korkeus = ruudukko.getKorkeus();
        do {
            ruudukko.arvoEsteet(esteenTn);
        } while ( !(ruudukko.setLahtoTyhjaan(1, 1, 3, 3) && 
                ruudukko.setMaaliTyhjaan(leveys-5, korkeus-5, leveys-2, korkeus-2)));//Arvotaan kunnes lähtö ja maali saadaan asetettua tyhjään ruutuun.
    }
    
    
    
    /**
     * Mittaa kuinka monta kertaa kukin algoritmi ehditään suoritaa parametrina annetussa ajassa.
     * Aikaa mitataan vain itse algoritmin suorituksesta.
     * Pseudokoodina:
     * 1: Arvo ruudukko
     * 2: Mittaa jokaisen algoritmin jota ei ole vielä suoritettu parametrina annettua aikaa suoritusaika tässä ruudukossa ja lisää se kokonaisaikaan
     * 3: Jos mitattavaa vielä jäljellä niin takaisin vaiheeseen 1
     * 4: Tulosta mittaustulokset
     * @param mittausAika Kuinka pitkään kutakin algoritmia mitataan millisekunteina.
     */
    public void vertaa(int mittausAika) {
        double[] mittausAjat = new double[algoritmit.length];
        int[] suoritusKertojenLukumaarat = new int[algoritmit.length];
        
        while (true) {
            arvoRuudukko();
            for (int moneskoAlgoritmi = 0; moneskoAlgoritmi < algoritmit.length; moneskoAlgoritmi++) {
                if (mittausAjat[moneskoAlgoritmi] > mittausAika) continue;
                algoritmit[moneskoAlgoritmi].alusta();
                mittausAjat[moneskoAlgoritmi] += algoritmit[moneskoAlgoritmi].suorita();
                if (mittausAjat[moneskoAlgoritmi] < mittausAika) suoritusKertojenLukumaarat[moneskoAlgoritmi]++;
            }
            int montakoMittaamatta = 0;
            for (int moneskoAlgoritmi = 0; moneskoAlgoritmi < mittausAjat.length; moneskoAlgoritmi++) {
                if (mittausAjat[moneskoAlgoritmi] < mittausAika) montakoMittaamatta++;
            }
            if (montakoMittaamatta == 0) break;
        }
        
        tulostaSuorituskertojenMaarat(mittausAika, suoritusKertojenLukumaarat);
    }
    
    
    private void tulostaSuorituskertojenMaarat(int mittausAika, int[] suoritusKertojenLukumaarat) {
        System.out.println("----------------------------");
        System.out.println("Vertailun tulokset "+ruudukonLeveys+"X"+ruudukonKorkeus+" ruudukossa "
                + "jossa esteitä generoitu todennäköisyydellä "+esteenTn);
        System.out.println("Algoritmien suoritustojen määrä " + mittausAika + " millisekunnissa:");
        for (int i = 0; i < algoritmit.length; i++) {
            System.out.println(algoritmit[i]+": " + suoritusKertojenLukumaarat[i]);
        }
        System.out.println("----------------------------");
    }
    
}
