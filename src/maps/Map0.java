package src.maps;

import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;


public class Map0 extends Maps {
    public Map0() {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();

        GameObject button = new GameObject(100, 200, 100, 100);
        button.behaviors.add(new ClickableArea(button, () -> {
            System.out.println("Clicou 0");
            Scene.selectedMapId = 1;
        }));
        objectsList.add(button);
    }
}
