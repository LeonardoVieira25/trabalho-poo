package src.behaviors;

import src.Janela;
import src.components.PhysicsObject;

public class BounceVertical extends Behavior {
    // private PhysicsObject physicsObject;
    private boolean hasEnteredScreen = false;

    public BounceVertical(PhysicsObject gameObject) {
        super(gameObject);
        // physicsObject = gameObject;
    }
    
    @Override
    public void update() {
        PhysicsObject physicsObject = (PhysicsObject) gameObject;
        if(hasEnteredScreen){
            if(physicsObject.positionY < 0){
                physicsObject.positionY = 0;
                physicsObject.velocityY *= -0.8;
                // System.out.println("Quicou"); #### --> abaixa numero de vidas de acordo com um contador. 
            }
            if(physicsObject.positionY + physicsObject.height > Janela.HEIGHT){
                physicsObject.positionY = Janela.HEIGHT - physicsObject.height;
                physicsObject.velocityY *= -1;
            }
        }else{
            if(physicsObject.positionY < Janela.HEIGHT){
                hasEnteredScreen = true;
            }
        }
    }

}
