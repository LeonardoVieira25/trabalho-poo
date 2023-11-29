package src.behaviors;

import src.components.GameObject;

public class Behavior {
    public GameObject gameObject;    
    public Behavior(GameObject gameObject){
        this.gameObject = gameObject;
    }
    public void update() {
    }
}
