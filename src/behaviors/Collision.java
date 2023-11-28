package src.behaviors;

import src.Janela;
import src.components.GameObject;
import src.components.PhysicsObject;

public class Collision extends Behavior {
    private PhysicsObject physicsObject;
    private boolean isValid = true;

    public Collision(PhysicsObject gameObject) {
        super(gameObject);

        if (gameObject == null) {
            System.out.println("null gameobject");
            isValid = false;
            return;
        }
        this.physicsObject = gameObject;
    }

    @Override
    public void update() {
    }

    public void collisionEntered(PhysicsObject otherPhysicsObject) {
        if (isValid) {
            float overlapX = (float) Math.abs((physicsObject.positionX + physicsObject.width / 2) - (otherPhysicsObject.positionX + otherPhysicsObject.width / 2.0));
            float overlapY = (float) Math.abs((physicsObject.positionY + physicsObject.height / 2) - (otherPhysicsObject.positionY + otherPhysicsObject.height / 2));
    
            float overlapWidth = (float)(physicsObject.width + otherPhysicsObject.width) / 2;
            float overlapHeight = (float)(physicsObject.height + otherPhysicsObject.height) / 2;
    
            // Verificar se há sobreposição e corrigir
            if (overlapX < overlapWidth && overlapY < overlapHeight) {
                float offsetX = overlapWidth - overlapX;
                float offsetY = overlapHeight - overlapY;
    
                if (offsetX < offsetY) {
                    if (physicsObject.positionX < otherPhysicsObject.positionX) {
                        physicsObject.positionX -= offsetX;
                        otherPhysicsObject.positionX += offsetX;
                    } else {
                        physicsObject.positionX += offsetX;
                        otherPhysicsObject.positionX -= offsetX;
                    }
                } else {
                    if (physicsObject.positionY < otherPhysicsObject.positionY) {
                        physicsObject.positionY -= offsetY;
                        otherPhysicsObject.positionY += offsetY;
                    } else {
                        physicsObject.positionY += offsetY;
                        otherPhysicsObject.positionY -= offsetY;
                    }
                }
    
                // Ajuste da velocidade após a colisão
                double tempVelocityX = physicsObject.velocityX;
                double tempVelocityY = physicsObject.velocityY;
    
                physicsObject.velocityX = otherPhysicsObject.velocityX;
                physicsObject.velocityY = otherPhysicsObject.velocityY;
    
                otherPhysicsObject.velocityX = tempVelocityX;
                otherPhysicsObject.velocityY = tempVelocityY;


            }
        }
    }
    
}
