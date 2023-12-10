/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.maps;

import src.Janela;
import src.MenuInicial;
import src.components.GameObject;
import src.components.Player;
import src.components.Spawner;
import src.exceptions.UserNotLogged;
import src.utils.XmlLoader;
import src.components.Contador;
import src.components.GameController;

public class Map1 extends Maps {

    public Map1() throws Exception {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();
        Player player = XmlLoader.getPlayer(MenuInicial.getUsername(), MenuInicial.getPassword());
        if (player == null) {
            throw new UserNotLogged();
        }
    }

    @Override
    public void onEnter() {
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();

        GameObject spawner1 = new Spawner(200, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner1);
        GameObject spawner2 = new Spawner(400, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner2);
        GameObject spawner3 = new Spawner(600, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner3);
        GameObject spawner4 = new Spawner(800, Janela.HEIGHT + 10, 100, 100, objectsListBuffer);
        objectsList.add(spawner4);

        eventManager.addListener(spawner1.getListener());
        eventManager.addListener(spawner2.getListener());
        eventManager.addListener(spawner3.getListener());
        eventManager.addListener(spawner4.getListener());

        Contador livesContador = new Contador("Vidas: ", "Vida", Janela.WIDTH - 100, Janela.HEIGHT - 100);
        objectsList.add(livesContador);
        eventManager.addListener(livesContador.getListener());

        Contador pointsContador = new Contador("Pontos: ", "Pontos", Janela.WIDTH - 100, Janela.HEIGHT - 200);
        objectsList.add(pointsContador);
        eventManager.addListener(pointsContador.getListener());

        Contador nivel = new Contador("Nivel: ", "Nivel", Janela.WIDTH - 100, Janela.HEIGHT - 300);
        objectsList.add(nivel);
        eventManager.addListener(nivel.getListener());

        Player player = XmlLoader.getPlayer(MenuInicial.getUsername(), MenuInicial.getPassword());
        if (player == null) {

        }
        player.setObjectsList(objectsListBuffer);
        objectsList.add(player);
        eventManager.addListener(player.getListener());

        GameController gameController = new GameController(objectsListBuffer);
        eventManager.addListener(gameController.getListener());
    }
}
