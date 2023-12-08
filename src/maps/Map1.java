package src.maps;


import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.behaviors.Collision;
import src.behaviors.SpawnGameObject;
import src.components.GameObject;
import src.components.Player;
import src.components.Spawner;
import src.utils.EventManager;
import src.components.Button;
import src.components.Contador;
import src.components.GameController;


public class Map1 extends Maps {
    public static EventManager eventManager = new EventManager();
    
    public Map1() {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();

        GameController gameController = new GameController(objectsList);
        objectsList.add(gameController);
        eventManager.addListener(gameController.getListener());
        
        GameObject spawner1 = new Spawner(200, Janela.HEIGHT+10, 100, 100, objectsListBuffer);
        objectsList.add(spawner1);
        GameObject spawner2 = new Spawner(400, Janela.HEIGHT+10, 100, 100, objectsListBuffer);
        objectsList.add(spawner2);
        GameObject spawner3 = new Spawner(600, Janela.HEIGHT+10, 100, 100, objectsListBuffer);
        objectsList.add(spawner3);

        Contador livesButton = new Contador("Vidas: ","vidaPlayer"/*vidas, mas tem que fazer ela diminuir em tempo real e tal*/, Janela.WIDTH-100, Janela.HEIGHT-100);
        objectsList.add(livesButton);
        
        Player player = new Player(objectsListBuffer);
        objectsList.add(player);
    }
}
