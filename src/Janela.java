package src;
import java.awt.*;
import java.util.List;

import javax.swing.*;

import src.components.GameObject;

public class Janela extends JFrame{
    public List<GameObject> renderingList;
    
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int HEIGHT = (int) screenSize.height/2;
    public static int WIDTH = (int) screenSize.width/2;
    
    public Janela() {
        System.out.println(screenSize.width + "x" + screenSize.height);

        

        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);
        setSize((int) screenSize.width/2,(int) screenSize.height/2);
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
    
    public void render(List<GameObject> renderingList){
        this.renderingList = renderingList;
        repaint();
    }
    
    
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(0, getHeight());
        for (GameObject gameObject : renderingList) {
            gameObject.draw(g2d);
        }
    }
}
