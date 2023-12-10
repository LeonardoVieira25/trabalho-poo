/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.behaviors;

import src.Scene;
import src.components.GameObject;

public class ClickableArea extends Behavior {
    public Runnable action;

    public ClickableArea(GameObject gameObject, Runnable action) {
        super(gameObject);
        this.action = action;
    }

    public boolean isInside(int x, int y) {
        return x >= gameObject.positionX && x <= gameObject.positionX + gameObject.width && y >= gameObject.positionY
                && y <= gameObject.positionY + gameObject.height;
    }

    private boolean pressing = false;

    @Override
    public void update() {
        gameObject.onHover = isInside(Scene.mousePositionX, Scene.mousePositionY);

        if (pressing && !(Scene.mousePressedX > 0) && !(Scene.mousePressedX > 0)) {
            pressing = false;
            if (isInside(Scene.mousePositionX, Scene.mousePositionY)) {
                action.run();
            }
        } else {
            pressing = isInside(Scene.mousePressedX, Scene.mousePressedY);
        }
    }
}
