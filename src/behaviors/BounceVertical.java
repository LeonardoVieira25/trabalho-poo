/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/

package src.behaviors;

import src.Janela;
import src.components.PhysicsObject;

public class BounceVertical extends Behavior {
    private boolean hasEnteredScreen = false;

    // * define uma ação que sera chama quando o objeto quicar */
    private Runnable onBounce = () -> {
    };

    public BounceVertical(PhysicsObject gameObject){
        super(gameObject);
    }
    public BounceVertical(PhysicsObject gameObject, Runnable onBounce){
        super(gameObject);
        this.onBounce = onBounce;
    }

    @Override
    public void update() {
        PhysicsObject physicsObject = (PhysicsObject) gameObject;
        if (hasEnteredScreen) {
            if (physicsObject.positionY < 0) {
                physicsObject.positionY = 0;
                physicsObject.velocityY *= -0.8;

                onBounce.run();
            }
            if (physicsObject.positionY + physicsObject.height > Janela.HEIGHT) {
                physicsObject.positionY = Janela.HEIGHT - physicsObject.height;
                physicsObject.velocityY *= -1;
            }
        } else {
            if (physicsObject.positionY + gameObject.height < Janela.HEIGHT) {
                hasEnteredScreen = true;
            }
        }
    }

}
