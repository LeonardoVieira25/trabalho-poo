package src.behaviors;

import src.Janela;
import src.components.PhysicsObject;

public class BounceHorizontal extends Behavior {
    private PhysicsObject physicsObject;
    public BounceHorizontal(PhysicsObject gameObject) {
        super(gameObject);
        this.physicsObject = gameObject;
    }


    @Override
    public void update() {
        if(this.physicsObject.positionX < 0){
            this.physicsObject.positionX = 0;
            this.physicsObject.velocityX *= -1;
        }
        if(this.physicsObject.positionX + this.physicsObject.width > Janela.WIDTH){
            this.physicsObject.positionX = Janela.WIDTH - this.physicsObject.width;
            this.physicsObject.velocityX *= -1;
        }
    }
}
