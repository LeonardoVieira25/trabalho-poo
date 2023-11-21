package src.maps;

import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;
import src.components.PhysicsObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Map1 extends Scene {
    public Map1(Janela janela) {
        super(janela);
        // List<GameObject> renderingList1 = new java.util.ArrayList<GameObject>();
        List<GameObject> updateList1 = new java.util.ArrayList<GameObject>();

        // PhysicsObject button = new PhysicsObject(10, 200, 100, 100);
        GameObject button = new GameObject(10, 200, 100, 100);
        button.behaviors.add(new ClickableArea(button, () -> {
            System.out.println("Clicou 1");
            button.removeNextIteration = true;
            // button.positionX += 100;
        }));
        // button.forceY = -25;

        // renderingList1.add(button);
        updateList1.add(button);

        // janela.renderingList = renderingList1;
        janela.renderingList = updateList1;
        updateList = updateList1;

        janela.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = -(e.getY() - Janela.HEIGHT);
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
                mousePositionY = -(e.getY() - Janela.HEIGHT);
                super.mouseMoved(e);
            }
        });
    }
}
