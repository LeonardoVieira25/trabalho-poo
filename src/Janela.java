/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src;

import java.awt.*;
import java.util.List;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import src.components.GameObject;

// public class Janela extends JFrame{
public class Janela extends JPanel implements KeyListener {
    public List<GameObject> renderingList;

    public static JFrame frame;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int HEIGHT = (int) screenSize.height;
    public static int WIDTH = (int) screenSize.width;

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (Scene.selectedMapId != 0) {
                Scene.selectedMapId = 0;
                System.out.println("Voltou ao menu");
            }
        }
    }
    
    public void keyReleased(KeyEvent key) {
    }
    
    public void keyTyped(KeyEvent key) {
        System.out.println(key.getKeyCode());
    }
    

    public Janela() {
        System.out.println(screenSize.width + "x" + screenSize.height);
        
        frame = new JFrame("Janela");
        
        setLayout(new FlowLayout());
        this.addKeyListener(this);
        this.setFocusable(true);
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

    public void render(List<GameObject> renderingList) {
        if (renderingList != null) {
            this.renderingList = renderingList;
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(new Color(55, 66, 91));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.translate(0, getHeight());

        if (renderingList != null) {
            for (GameObject gameObject : renderingList) {
                gameObject.draw(g2d);
            }
        }
    }
}
