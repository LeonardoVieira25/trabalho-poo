package src.behaviors;

import src.components.GameObject;
import src.components.PhysicsObject;

import java.util.List;


public class SpawnGameObject extends Behavior {
    private List<GameObject> objectsList;
    // public GameObject objectToSpawn;
    
    public SpawnGameObject(GameObject gameObject, List<GameObject> objectsList) {
        super(gameObject);
        this.objectsList = objectsList;
        // this.objectToSpawn = objectToSpan;
        // this.objectToSpawn.positionX = gameObject.positionX;
        // this.objectToSpawn.positionY = gameObject.positionY;
    }
    private double accumulatedTime = 0;
    @Override
    public void update(){
        accumulatedTime += gameObject.deltaTimeRender;
        if(accumulatedTime > 0.01){
            System.out.println("criar bloco");
            PhysicsObject newInstance = new PhysicsObject(gameObject.positionX, gameObject.positionY, 5, 5);
            newInstance.forceX = (Math.random() * 50 - 25);
            newInstance.forceY = (Math.random() * 50 - 25);

            newInstance.behaviors.add(new BounceVertical(newInstance));
            newInstance.behaviors.add(new BounceHorizontal(newInstance));
            newInstance.behaviors.add(new ClickableArea(newInstance, () -> {
                newInstance.removeNextIteration = true;
            }));
            
            
            objectsList.add(newInstance);
            accumulatedTime = 0;
        }
    }
}
