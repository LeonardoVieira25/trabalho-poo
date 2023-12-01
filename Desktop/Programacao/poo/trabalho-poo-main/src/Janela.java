package src;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.util.List;

import javax.swing.*;

import src.components.GameObject;

// public class Janela extends JFrame{
public class Janela extends JPanel implements KeyListener{
    public List<GameObject> renderingList;
    
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int HEIGHT = (int) screenSize.height;
    public static int WIDTH = (int) screenSize.width;
    
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE){
            if (Scene.selectedMapId != 0){
                Scene.selectedMapId = 0;
                System.out.println("Voltou ao menu");
            }
        }
    }

    public void keyReleased(KeyEvent key){}

    public void keyTyped(KeyEvent key){}

    public Janela() {
        System.out.println(screenSize.width + "x" + screenSize.height);
        
        JFrame frame = new JFrame("Janela");
        frame.addKeyListener(this);

        setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);
        setVisible(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                HEIGHT = getHeight();
                WIDTH = getWidth();
            }
        });
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
    public void render(List<GameObject> renderingList){
        if(renderingList != null){
            this.renderingList = renderingList;
            repaint();
        }
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.translate(0, getHeight());
        
        if(renderingList != null){
            for (GameObject gameObject : renderingList) {
                gameObject.draw(g2d);
            }
        }
    }

}
