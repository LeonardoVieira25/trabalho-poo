package src.maps;

import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.behaviors.SpawnBlocks;
import src.components.GameObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Map1 extends Scene {
    public Map1(Janela janela) {
        super(janela);
        List<GameObject> objectsList = new java.util.ArrayList<GameObject>();
        List<GameObject> objectsListBuffer = new java.util.ArrayList<GameObject>();

        GameObject button = new GameObject(10, 200, 100, 100);
        button.behaviors.add(new ClickableArea(button, () -> {
            System.out.println("Clicou 1");
            button.removeNextIteration = true;
        }));
        objectsList.add(button);

        GameObject spawner = new GameObject(200,200,50,50);
        spawner.behaviors.add(new SpawnBlocks(spawner, objectsListBuffer));

        objectsList.add(spawner);




        janela.renderingList = objectsList;
        janela.renderingListBuffer = objectsListBuffer;
        updateList = objectsList;
        updateListBuffer = objectsListBuffer;

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
