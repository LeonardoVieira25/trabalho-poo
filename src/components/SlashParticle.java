package src.components;

import src.behaviors.Collision;
import src.behaviors.DestroyAfterTime;

public class SlashParticle extends PhysicsObject {
    public SlashParticle(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        this.velocityX = (Math.random() * 100 - 50);
        this.velocityY = (Math.random() * 100 - 50);
        this.behaviors.add(new DestroyAfterTime(this, 0.5));
        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            return !(otherPhysicsObject instanceof SlashParticle || otherPhysicsObject instanceof Player);
        }));
    }
    @Override
    public void update() {
        super.update();
        this.velocityX -= 1*deltaTimeRender;
        this.velocityY -= 1*deltaTimeRender;
    }

}
