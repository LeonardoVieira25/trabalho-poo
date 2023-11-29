package src.behaviors;

import src.components.GameObject;

public class DestroyAfterTime extends Behavior {
    private double timeToDestroy;
    public DestroyAfterTime(GameObject gameObject, double timeToDestroy) {
        super(gameObject);
        this.timeToDestroy = timeToDestroy;
    }
    private double accumulatedTime = 0;
    @Override
    public void update() {
        super.update();
        if(this.accumulatedTime > timeToDestroy){
            gameObject.removeNextIteration = true;
            return;
        }
        this.accumulatedTime += gameObject.deltaTimeRender;
    }
}
