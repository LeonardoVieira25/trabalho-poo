package src.behaviors;

import src.components.GameObject;
import src.components.PhysicsObject;

import java.util.List;


public class SpawnBlocks extends Behavior {
    private List<GameObject> renderList;
    public SpawnBlocks(GameObject gameObject, List<GameObject> renderList) {
        super(gameObject);
        this.renderList = renderList;
    }
    private double accumulatedTime = 0;
    @Override
    public void update(){
        accumulatedTime += gameObject.deltaTimeRender;
        if(accumulatedTime > 1){
            System.out.println("criar bloco");
            PhysicsObject newBlock = new PhysicsObject(gameObject.positionX, gameObject.positionY, 10, 10);
            newBlock.velocityX = 100;
            renderList.add(newBlock);
            accumulatedTime = 0;
        }
    }
}
