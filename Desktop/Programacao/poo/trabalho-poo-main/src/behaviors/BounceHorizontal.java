package src.behaviors;

import src.Janela;
import src.components.PhysicsObject;

public class BounceHorizontal extends Behavior {
    // private PhysicsObject physicsObject;
    public BounceHorizontal(PhysicsObject gameObject) {
        super(gameObject);
    }
    
    
    @Override
    public void update() {
        PhysicsObject physicsObject = (PhysicsObject) this.gameObject;
        if(physicsObject.positionX < 0){
            physicsObject.positionX = 0;
            physicsObject.velocityX *= -1;
        }
        if(physicsObject.positionX + physicsObject.width > Janela.WIDTH){
            physicsObject.positionX = Janela.WIDTH - physicsObject.width;
            physicsObject.velocityX *= -1;
        }
    }
}
