package src;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Janela extends JFrame{
    public List<GameObject> renderingList = new ArrayList<GameObject>();
    public Janela() {
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(){
        repaint();
    }
 
    
    public void paint(Graphics g) {
        super.paint(g);
        for (GameObject gameObject : renderingList) {
            gameObject.draw(g);
        }
    }
}