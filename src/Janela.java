package src;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import src.components.GameObject;

public class Janela extends JFrame{
    public List<GameObject> renderingList = new ArrayList<GameObject>();
    public static int HEIGHT = 0;
    public static int WIDTH = 0;
    public Janela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.width + "x" + screenSize.height);

        

        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);
        setSize((int) screenSize.width/2,(int) screenSize.height/2);
        HEIGHT = (int) screenSize.height/2;
        WIDTH = (int) screenSize.width/2;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                HEIGHT = getHeight();
                WIDTH = getWidth();
            }
        });
    }
    
    public void render(){
        repaint();
    }
    
    
    private List<GameObject> ObjectsToRemove = new ArrayList<GameObject>();
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(0, getHeight());
        for (GameObject gameObject : renderingList) {
            if(gameObject.removeNextIteration){
                ObjectsToRemove.add(gameObject);
            }else{
                gameObject.draw(g2d);
            }
        }
        if(ObjectsToRemove.size() > 0){
            renderingList.removeAll(ObjectsToRemove);
            ObjectsToRemove.clear();
        }
    }
}