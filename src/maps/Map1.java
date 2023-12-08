package src.maps;

import src.Janela;
import src.components.GameObject;
import src.components.Player;
import src.components.Spawner;
import src.exceptions.UserNotLogged;
import src.utils.XmlLoader;
import src.components.Contador;
import src.components.GameController;

public class Map1 extends Maps {

    public Map1(String username, String password) throws Exception {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();

        GameObject spawner1 = new Spawner(200, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner1);
        GameObject spawner2 = new Spawner(400, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner2);
        GameObject spawner3 = new Spawner(600, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner3);

        Contador livesContador = new Contador("Vidas: ", "Vida", Janela.WIDTH - 100, Janela.HEIGHT - 100);
        objectsList.add(livesContador);
        eventManager.addListener(livesContador.getListener());

        Contador pointsContador = new Contador("Pontos: ", "pontos", Janela.WIDTH - 100, Janela.HEIGHT - 200);
        objectsList.add(pointsContador);
        eventManager.addListener(pointsContador.getListener());

        Player player = XmlLoader.getPlayer(username, password);
        if (player == null) {
            throw new UserNotLogged();
        }
        player.setObjectsList(objectsListBuffer);
        objectsList.add(player);

        GameController gameController = new GameController(objectsList);
        eventManager.addListener(gameController.getListener());
    }
}
