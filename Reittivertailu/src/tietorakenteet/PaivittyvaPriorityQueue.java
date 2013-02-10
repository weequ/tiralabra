/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Antti
 */
public class PaivittyvaPriorityQueue<E> extends PriorityQueue<E> implements PaivittyvaPrioriteettiJono<E> {

    public PaivittyvaPriorityQueue() {
        super();
    }
    
    public PaivittyvaPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        super(initialCapacity, comparator);
    }
    
    @Override
    public void update(E e) {
        remove(e);
        offer(e);
    }
    
}
