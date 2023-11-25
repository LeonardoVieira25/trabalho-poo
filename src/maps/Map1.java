package src.maps;

import src.Janela;
import src.behaviors.SpawnGameObject;
import src.components.GameObject;


public class Map1 extends Maps {
    public Map1() {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();

        // GameObject button = new GameObject(200, 200, 100, 100);
        // button.behaviors.add(new ClickableArea(button, () -> {
        //     System.out.println("Clicou 1");
        //     Scene.selectedMapId = 0;
        // }));
        // objectsList.add(button);

        GameObject spawner = new GameObject(200, Janela.HEIGHT+10, 100, 100);
        spawner.behaviors.add(new SpawnGameObject(spawner, objectsListBuffer));
        objectsList.add(spawner);
        

    }
}
