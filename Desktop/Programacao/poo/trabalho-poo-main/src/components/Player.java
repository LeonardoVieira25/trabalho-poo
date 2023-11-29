package src.components;

import java.util.List;

import src.Scene;

public class Player extends PhysicsObject {
    private List<GameObject> objectsList;
    private int lastPositionX = 0;
    private int lastPositionY = 0;

    public Player(List<GameObject> objectsList){
        super(0, 0, 10, 10);
        this.objectsList = objectsList;
    }
    private float accumulatedTime = 0;

    @Override
    public void update() {
        super.update();
        int variationX = Scene.mousePositionX - lastPositionX;
        int variationY = Scene.mousePositionY - lastPositionY;

        this.positionX = Scene.mousePositionX - 5;
        this.positionY = Scene.mousePositionY - 5;

        this.lastPositionX = Scene.mousePositionX;
        this.lastPositionY = Scene.mousePositionY;

        float velocity = (float) Math.sqrt(Math.pow(variationX, 2) + Math.pow(variationY, 2));

        // System.out.println(velocity);

        if(this.accumulatedTime > 0.001-velocity/100 && velocity > 0){
            PhysicsObject newInstance = new SlashParticle(this.positionX, this.positionY, 5, 5);
            this.objectsList.add(newInstance);
            this.accumulatedTime = 0;
        }else{
            this.accumulatedTime += this.deltaTimeRender;
        }

    }
}
