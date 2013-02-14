/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

/**
 * Määrittelee päivittyvän prioriteettijonon operaatiot.
 * @author Antti
 */
public interface PaivittyvaPrioriteettiJono<T> {
    
    public void update(T e);
    
    public boolean offer(T e);
    
    public T poll();
    
    public int size();
    
    public void clear();
}
