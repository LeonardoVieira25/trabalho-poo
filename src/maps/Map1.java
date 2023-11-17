package src.maps;

import src.GameObject;
import src.Janela;
import src.PhysicsObject;
import src.Scene;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map1 extends Scene {
    public Map1(Janela janela) {
        super(janela);
        janela.renderingList.add(new GameObject(0, 700, 1000, 20));
        
        ClickableArea clickableArea = new ClickableArea(100, 500, 200, 200, () -> {
            System.out.println("Clicou");
        });

        janela.renderingList.add(clickableArea);
        updateList.add(clickableArea);

        janela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PhysicsObject novoObjeto = new PhysicsObject(e.getX(), e.getY(), 100, 100);
                novoObjeto.forceY = (300);
                novoObjeto.velocityX = (800);
                janela.renderingList.add(novoObjeto);
                updateList.add(novoObjeto);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressedX = -1;
                mousePressedY = -1;
                super.mouseReleased(e);
            }
            
        });
        janela.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePositionX = e.getX();
                mousePositionY = e.getY();
                super.mouseMoved(e);
            }
        });
    }
}
