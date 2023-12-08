package src.components.inimigos;

import src.components.PhysicsObject;

public class Inimigo extends PhysicsObject {
    private int dano = 1;
    public int getDano() {
        return dano;
    }
    Inimigo(double positionX, double positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }
}
