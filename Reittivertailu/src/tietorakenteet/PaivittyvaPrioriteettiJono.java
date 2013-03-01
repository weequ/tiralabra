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
    
    /**
     * Päivittää alkion sijainnin prioriteettijonossa. Kutsu tätä metodia kun olet pienentänyt avainta.
     * @param e Alkio joka päivitetään
     */
    public void update(T e);
    
    /**
     * Lisää alkion prioriteettijonoon.
     * @param e Alkio joka lisätään
     * @return True jos lisäys onnistuu.
     */
    public boolean offer(T e);
    
    /**
     * Poistaa ja palauttaa Prioriteettijonon ensimmäisen alkion.
     * @return Poistettu alkio.
     */
    public T poll();
    
    /**
     * Palauttaa kuinka monta alkiota prioriteettijono sisältää.
     * @return 
     */
    public int size();
    
    /**
     * Tyhjentää prioriteettijonon.
     */
    public void clear();
}
