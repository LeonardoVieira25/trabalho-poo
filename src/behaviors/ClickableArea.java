package src.behaviors;

import src.Scene;
import src.components.GameObject;

public class ClickableArea extends Behavior {
// public class ClickableArea extends PhysicsObject {
    public Runnable action;
    public ClickableArea(GameObject gameObject,Runnable action) {
        // super(x, y, width, height);
        // this.gameObject = gameObject;
        super(gameObject);
        this.action = action;
    }

    public boolean isInside(int x, int y) {
        return x >= gameObject.positionX && x <= gameObject.positionX + gameObject.width && y >= gameObject.positionY && y <= gameObject.positionY + gameObject.height;
    }

    @Override
    public void update() {
        // if(isInside(Scene.mousePositionX, Scene.mousePositionY)){
        //     gameObject.color = java.awt.Color.RED;
        // }else{
        //     gameObject.color = java.awt.Color.gray;
        // }
        gameObject.onHover = isInside(Scene.mousePositionX, Scene.mousePositionY);
        if (isInside(Scene.mousePressedX, Scene.mousePressedY)) {
            action.run();
        }

    }
}
