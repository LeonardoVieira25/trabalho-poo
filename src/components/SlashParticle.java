package src.components;

import java.awt.Color;

import src.behaviors.Collision;
import src.behaviors.DestroyAfterTime;

public class SlashParticle extends PhysicsObject {
    public SlashParticle(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height);
        this.velocityX = (Math.random() * 100 - 50)*2;
        this.velocityY = (Math.random() * 100 - 50)*2;
        this.behaviors.add(new DestroyAfterTime(this, 2));
        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            return !(otherPhysicsObject instanceof SlashParticle || otherPhysicsObject instanceof Player);
        }, true));
        this.width = (Math.random()+1)*10;
        this.height = (Math.random()+1)*10;
        Color flameColors [] = {Color.RED, Color.ORANGE, Color.YELLOW};
        this.color = flameColors[(int)(Math.random()*3)];
    }
    @Override
    public void update() {
        super.update();
        this.velocityX -= 1*deltaTimeRender;
        this.velocityY -= 1*deltaTimeRender;
    }

}
