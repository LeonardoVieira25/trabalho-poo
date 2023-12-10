/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.behaviors;

import src.components.GameObject;

public class Behavior implements Cloneable {
    @Override
    public Behavior clone() {
        try {
            Behavior clone = (Behavior) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            System.err.println("Error cloning Behavior");
            throw new AssertionError(); // Can't happen
        }
    }

    public GameObject gameObject;

    public Behavior(GameObject gameObject) {
        this.gameObject = gameObject;
    }


    public void update() {
    }
}
