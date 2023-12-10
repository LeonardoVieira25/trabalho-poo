package src.components.inimigos;

import java.awt.Image;

import src.components.PhysicsObject;
import src.maps.Maps;

public class Inimigo extends PhysicsObject {
    private int dano = 1;
    protected Image image;

    public int getDano() {
        return dano;
    }
    Inimigo(double positionX, double positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }
    private boolean fistIterationAfterPause = true;
    @Override
    public void update() {
        if(Maps.isPaused) {
            fistIterationAfterPause = true;
            return;
        }else if(fistIterationAfterPause){
            fistIterationAfterPause = false;
            resetDeltaTimeRender();
            return;
        }
        super.update();
    }
}
