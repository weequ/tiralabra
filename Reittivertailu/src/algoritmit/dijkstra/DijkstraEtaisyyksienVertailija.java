/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmit.dijkstra;

import java.util.Comparator;
import ruudukko.Ruutu;

/**
 *
 * @author Antti
 */
public class DijkstraEtaisyyksienVertailija implements Comparator<Ruutu> {

    @Override
    public int compare(Ruutu r1, Ruutu r2) {
        return (int) (r1.getEtaisyysAlusta()-r2.getEtaisyysAlusta());
    }


    
}
