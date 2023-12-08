package src.components;

import java.util.List;

public class GameController extends GameObject {
    private List<GameObject> objectsList;
    private int lives = 10;
    private int points = 0;

    public GameController(List<GameObject> objectsList) {
        super(0, 0, 0, 0);
        this.objectsList = objectsList;
    }

    public void update() {
        for (GameObject gameObject : objectsList) {
            if (gameObject instanceof Contador) {
                if (((Contador) gameObject).getId().equals("vidaPlayer")) {

                    ((Contador) gameObject).updateValue(lives + "");
                }
                if (((Contador) gameObject).getId().equals("ponitsPlayer")) {

                    ((Contador) gameObject).updateValue(points + "");
                }
            }
        }
        super.update();
    }

    public void draw(java.awt.Graphics2D g2d) {
        super.draw(g2d);
    }
}
