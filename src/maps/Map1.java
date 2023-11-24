package src.maps;

import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;


public class Map1 extends Maps {
    public Map1() {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();
        GameObject button = new GameObject(200, 200, 100, 100);

        button.behaviors.add(new ClickableArea(button, () -> {
            System.out.println("Clicou 1");
            Scene.selectedMapId = 0;
        }));
        objectsList.add(button);
    }
}
