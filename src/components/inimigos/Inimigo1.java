package src.components.inimigos;

import src.behaviors.Behavior;
import src.behaviors.BounceHorizontal;
import src.behaviors.BounceVertical;
import src.behaviors.Collision;
import src.components.GameObject;
import src.components.PhysicsObject;
import src.components.SlashParticle;

public class Inimigo1 extends PhysicsObject {
    @Override
    public Object clone() {
        PhysicsObject clone = new PhysicsObject(positionX, positionY, width, height);
        clone.velocityX = this.velocityX;
        clone.forceX = this.forceX;
        clone.velocityY = this.velocityY;
        clone.forceY = this.forceY;
        clone.drag = this.drag;
        
        for (Behavior behavior : this.behaviors) {
            Behavior cloneBehavior = behavior.clone();
            cloneBehavior.gameObject = clone;

            clone.behaviors.add(cloneBehavior);
        }
        return clone;
    }
    public Inimigo1(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
        this.behaviors.add(new BounceVertical(this));
        this.behaviors.add(new BounceHorizontal(this));
        this.forceY = -50;
        this.forceX = 0;

        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            return otherPhysicsObject instanceof SlashParticle;
        }));
    }
}
