package src.maps;

import src.GameObject;
import src.Scene;

public class ClickableArea extends GameObject {
    public Runnable action;

    public ClickableArea(int x, int y, int width, int height, Runnable action) {
        super(x, y, width, height);
        this.action = action;
    }

    public boolean isInside(int x, int y) {
        return x >= this.positionX && x <= this.positionX + this.width && y >= this.positionY && y <= this.positionY + this.height;
    }

    @Override
    public void update() {
        if(isInside(Scene.mousePositionX, Scene.mousePositionY)){
            this.color = java.awt.Color.RED;
            // System.out.println("hover");
        }else{
            this.color = java.awt.Color.BLACK;
        }
        if (Scene.mousePressedX != -1 && Scene.mousePressedY != -1 && isInside(Scene.mousePressedX, Scene.mousePressedY)) {
            action.run();
        }
    }
}
