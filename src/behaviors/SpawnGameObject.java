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
    }
    private double accumulatedTime = 0;
    @Override
    public void update(){
        accumulatedTime += gameObject.deltaTimeRender;
        if(accumulatedTime > 1){
            System.out.println("criar bloco");


            //* Nessa parte que gostaria de clonar um objeto passado como argumento
            //* Esse objeto seria uma classe que estende GameObject chamada objectToSpawn
            //* que eu poderia alterar dentro do GameObject que possui essa behavior
            //* Dessa forma conseguiria criar vários objetos que representariam entidades diferentes
            //* como um inimigo, um bloco, uma moeda, etc.
            
            PhysicsObject newInstance = new PhysicsObject(gameObject.positionX, gameObject.positionY, 50, 50);
            newInstance.velocityX = (Math.random() * 100 - 50);
            newInstance.velocityY = (Math.random() * 100 - 50);
            newInstance.forceY = -100;


            newInstance.behaviors.add(new BounceVertical(newInstance));
            newInstance.behaviors.add(new BounceHorizontal(newInstance));
            newInstance.behaviors.add(new ClickableArea(newInstance, () -> {
                newInstance.removeNextIteration = true;
            }));
            newInstance.behaviors.add(new Collision(newInstance));
            
            
            objectsList.add(newInstance);
            accumulatedTime = 0;
        }
    }
}
