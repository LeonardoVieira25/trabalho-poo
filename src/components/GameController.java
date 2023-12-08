package src.components;

import java.util.List;

import src.maps.Maps;

public class GameController extends GameObject {
    private List<GameObject> objectsList;
    private int lives = 10;
    private int points = 0;

    public GameController(List<GameObject> objectsList) {
        super(0, 0, 0, 0);
        this.objectsList = objectsList;
        Maps.eventManager.trigger("Vida:" + lives);
        // listener = (event) -> {
        //     if (event instanceof String) {
        //         String eventString = (String) event;
        //         if (eventString.equals("inimigo1Morreu")) {
        //             points += 10;
        //             Maps.eventManager.trigger("Pontos:" + points);
        //         }
        //         if (eventString.equals("inimigoBateu")) {
        //             lives--;
        //             Maps.eventManager.trigger("setLives:" + lives);
        //             if (lives <= 0) {
        //                 Maps.eventManager.trigger("gameOver");
        //             }
        //         }
        //     }
        // };
    }

    public void update() {
        // for (GameObject gameObject : objectsList) {
        //     if (gameObject instanceof Contador) {
        //         if (((Contador) gameObject).getId().equals("vidaPlayer")) {

        //             ((Contador) gameObject).updateValue(lives + "");
        //         }
        //         if (((Contador) gameObject).getId().equals("ponitsPlayer")) {

        //             ((Contador) gameObject).updateValue(points + "");
        //         }
        //     }
        // }
        // super.update();
    }

    public void draw(java.awt.Graphics2D g2d) {
        // super.draw(g2d);
    }
}
