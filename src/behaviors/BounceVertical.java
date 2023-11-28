package src.behaviors;

import src.Janela;
import src.components.PhysicsObject;

public class BounceVertical extends Behavior {
    private PhysicsObject physicsObject;
    private boolean hasEnteredScreen = false;

    public BounceVertical(PhysicsObject gameObject) {
        super(gameObject);
        this.physicsObject = gameObject;
    }

    @Override
    public void update() {
        if(hasEnteredScreen){
            if(this.physicsObject.positionY < 0){
                this.physicsObject.positionY = 0;
                this.physicsObject.velocityY *= -0.8;
            }
            if(this.physicsObject.positionY + this.physicsObject.height > Janela.HEIGHT){
                this.physicsObject.positionY = Janela.HEIGHT - this.physicsObject.height;
                this.physicsObject.velocityY *= -1;
            }
        }else{
            if(this.physicsObject.positionY < Janela.HEIGHT){
                hasEnteredScreen = true;
            }
        }
    }

}
