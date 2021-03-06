/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Ei kuulu kurssiin. Jätetty ohjelmaan vaihtoehtoiseksi tietorakenteeksi vertailun vuoksi.
 * Javan priorityqueueen lisätty alkion päivitysominaisuus.
 * @author Antti
 */
public class PaivittyvaPriorityQueue<E> extends PriorityQueue<E> implements PaivittyvaPrioriteettiJono<E> {
    
    public PaivittyvaPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        super(initialCapacity, comparator);
    }
    
    /**
     * 
     * @param e Alkio joka halutaan päivittää.
     */
    @Override
    public void update(E e) {//Javan priority queue ei tue decrease-key operaatiota.
        remove(e);//O(n)
        offer(e);//O(log(n))
    }
    
}
