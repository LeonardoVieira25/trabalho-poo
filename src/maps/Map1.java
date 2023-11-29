package src.maps;

import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.behaviors.Collision;
import src.behaviors.SpawnGameObject;
import src.components.GameObject;
import src.components.PhysicsObject;
import src.components.Player;
import src.components.Button;

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
        // Nota: Janela.HEIGHT/WIDTH nos da metade do tamanho da tela, por isso esta multiplicado por 
        // 2 logo abaixo
        objectsList.add(spawner);
        Button menu = new Button("Menu", Janela.WIDTH*2 - 200, Janela.HEIGHT*2-100, 150, 50);
        menu.behaviors.add(new ClickableArea(menu, () -> {
            System.out.println("Back to Menu");
            Scene.selectedMapId = 0;
        }));
        objectsList.add(menu);
    }
}
