/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.behaviors;

import java.util.List;
import java.util.function.Function;

import src.components.GameObject;


public class SpawnGameObject extends Behavior {
    private List<GameObject> objectsList;
    private List<GameObject> objectsToSpaw;
    public GameObject objectToSpawn;
    public float timeToSpawn = 0.5f;
    private Function<GameObject, Void> beforeSpawn;
    
    public SpawnGameObject(GameObject gameObject, List<GameObject> objectsList, float timeToSpawn, GameObject objectToSpawn) {
        super(gameObject);
        this.objectsList = objectsList;
        this.timeToSpawn = timeToSpawn;
        this.objectToSpawn = objectToSpawn;
    }
    public SpawnGameObject(GameObject gameObject, List<GameObject> objectsList, float timeToSpawn, List<GameObject> objectsToSpaw) {
        super(gameObject);
        this.objectsList = objectsList;
        this.timeToSpawn = timeToSpawn;
        this.objectsToSpaw = objectsToSpaw;
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
    public SpawnGameObject(
        GameObject gameObject, List<GameObject> objectsList, float timeToSpawn, List<GameObject> objectsToSpaw, Function<GameObject, Void> beforeSpawn
        ) {
        super(gameObject);
        this.objectsList = objectsList;
        this.timeToSpawn = timeToSpawn;
        this.objectsToSpaw = objectsToSpaw;
        this.beforeSpawn = beforeSpawn;
    }
	private double accumulatedTime = 0;
    @Override
    public void update(){
        accumulatedTime += gameObject.deltaTimeRender;
        if(accumulatedTime > this.timeToSpawn){
            //* se a lista de objects to spawn não for nula, escolher um objeto aleatório da mesma */
            GameObject newInstance;
            if(this.objectsToSpaw != null){
                int randomIndex = (int) (Math.random() * this.objectsToSpaw.size());
                this.objectToSpawn = this.objectsToSpaw.get(randomIndex);
                newInstance = (GameObject) this.objectToSpawn.clone();
            }else{
                newInstance = (GameObject) this.objectToSpawn.clone();
            }
            
            if(this.beforeSpawn != null){
                this.beforeSpawn.apply(newInstance);
            }
            
            objectsList.add(newInstance);
            accumulatedTime = 0;
        }
    }
}
