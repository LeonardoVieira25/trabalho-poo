package src.maps;

import src.Janela;
import src.behaviors.Collision;
import src.behaviors.SpawnGameObject;
import src.components.GameObject;
import src.components.PhysicsObject;
import src.components.Player;


public class Map1 extends Maps {
    public Map1() {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();


        GameObject spawner = new GameObject(200, Janela.HEIGHT+10, 100, 100);
        spawner.behaviors.add(new SpawnGameObject(spawner, objectsListBuffer));

        //* Aqui eu poderia controlar qual objeto seria criado de acordo com tempo de jogo, nível, etc.
        //* spawner.objectToSpawn = new AlgumTipoDeObjeto(args...);
        Player player = new Player(objectsListBuffer);
        objectsList.add(player);

        objectsList.add(spawner);
    }
}
