package src.behaviors;

import java.util.List;
import java.util.function.Function;

import src.components.GameObject;


public class SpawnGameObject extends Behavior {
    private List<GameObject> objectsList;
    public GameObject objectToSpawn;
    public float timeToSpawn = 0.5f;
    private Function<GameObject, Void> beforeSpawn;
    
    public SpawnGameObject(GameObject gameObject, List<GameObject> objectsList, float timeToSpawn, GameObject objectToSpawn) {
        super(gameObject);
        this.objectsList = objectsList;
        this.timeToSpawn = timeToSpawn;
        this.objectToSpawn = objectToSpawn;
    }
    public SpawnGameObject(
        GameObject gameObject, List<GameObject> objectsList, float timeToSpawn, GameObject objectToSpawn, Function<GameObject, Void> beforeSpawn
        ) {
        super(gameObject);
        this.objectsList = objectsList;
        this.timeToSpawn = timeToSpawn;
        this.objectToSpawn = objectToSpawn;
        this.beforeSpawn = beforeSpawn;
    }
	private double accumulatedTime = 0;
    @Override
    public void update(){
        accumulatedTime += gameObject.deltaTimeRender;
        if(accumulatedTime > this.timeToSpawn){
            // System.out.println("criar bloco");
            GameObject newInstance = (GameObject) objectToSpawn.clone();
            
            if(this.beforeSpawn != null){
                this.beforeSpawn.apply(newInstance);
            }
            
            objectsList.add(newInstance);
            accumulatedTime = 0;
        }
    }
}
