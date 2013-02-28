/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import algoritmit.AhneLyhimmanPolunAlgoritmi;
import algoritmit.BellmanFord;
import algoritmit.LyhimmanPolunAlgoritmi;
import algoritmit.aTahti.ATahti;
import algoritmit.dijkstra.Dijkstra;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import reittivertailu.Reittivertailu;
import ruudukko.Ruudukko;

/**
 * Keskeneräinen!!!
 * @author Antti
 */
public class GUI extends JFrame {
    
    Reittivertailu reittivertailu;
    KarttaKangas karttaKangas;
    JPanel paaPaneeli, oikeaSivuPaneeli;
    LyhimmanPolunAlgoritmi algoritmi;
    
    public GUI(Ruudukko ruudukko) {
        algoritmi = new Dijkstra(ruudukko, AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI);
        algoritmi.alusta();
        karttaKangas = new KarttaKangas(ruudukko);
        setTitle("");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lisaaKomponentit();
        //setLayout(new FlowLayout());
    }
    
    
    private void lisaaKomponentit() {
        paaPaneeli = new JPanel();
        oikeaSivuPaneeli = new JPanel();
        oikeaSivuPaneeli.setLayout(new GridLayout(6, 1));
        oikeaSivuPaneeli.setBackground(Color.WHITE);
        paaPaneeli.add(karttaKangas);
        paaPaneeli.add(oikeaSivuPaneeli);
        lisaaNappulat();
        JScrollPane ikkuna = new JScrollPane(paaPaneeli);
        add(ikkuna);
        paaPaneeli.setAutoscrolls(false);
        paaPaneeli.setBackground(Color.WHITE);
    }
    
    private void lisaaNappulat() {
        final JButton suorita = new JButton("Suorita");
        
        suorita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suorita.setText("Suoritetaan...");
                algoritmi.lopetaAnimointi();
                algoritmi.alusta();
                suorita.setText("Suoritettu ajassa "+algoritmi.suorita()+ "ms");
            }
        });
        
        JButton etene = new JButton("Etene");
        etene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algoritmi.etene();
            }
        });
        
        final JSpinner viive = new JSpinner(new SpinnerNumberModel(50, 5, 2000, 5));
        
        JButton animoi = new JButton("Animoi");
        animoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algoritmi.lopetaAnimointi();
                algoritmi.suorita((int) viive.getValue());
            }
        });
        
        
        final JComboBox<String> algoritmit = new JComboBox<>(new String[] {"Dijkstra","ATahti","Bellman-Ford"});
        algoritmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (algoritmit.getSelectedItem().equals("Dijkstra")) {
                    algoritmi = new Dijkstra(karttaKangas.getRuudukko(), AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI);
                } else if (algoritmit.getSelectedItem().equals("ATahti")) {
                     algoritmi = new ATahti(karttaKangas.getRuudukko(), AhneLyhimmanPolunAlgoritmi.JonoTyyppi.BINAARI);
                } if (algoritmit.getSelectedItem().equals("Bellman-Ford")) {
                    algoritmi = new BellmanFord(karttaKangas.getRuudukko());
                }
                algoritmi.alusta();
                karttaKangas.repaint();
            }
        });
        
        JButton tyhjenna = new JButton("Tyhjenna");
        tyhjenna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algoritmi.alusta();
                //algoritmit.actionPerformed(null);
            }
        });
        
        //algoritmit.actionPerformed(null);//Alustaa oikean algoritmin
        //JSpinner viive = new JSpinner(new SpinnerNumberModel(100, 10, 2000, 10));
        
        oikeaSivuPaneeli.add(algoritmit);
        oikeaSivuPaneeli.add(viive);
        oikeaSivuPaneeli.add(animoi);
        oikeaSivuPaneeli.add(suorita);
        oikeaSivuPaneeli.add(etene);
        oikeaSivuPaneeli.add(tyhjenna);
    }
    
    
    public void setRuudukko(Ruudukko ruudukko) {
        karttaKangas.setRuudukko(ruudukko);
    }
    
    public LyhimmanPolunAlgoritmi getAlgoritmi() {
        return algoritmi;
    }
    
    
    
}
