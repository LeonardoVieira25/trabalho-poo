/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.components;

import java.awt.Color;

import src.behaviors.Collision;
import src.behaviors.DestroyAfterTime;

public class SlashParticle extends PhysicsObject {
    public SlashParticle(double positionX, double positionY, double width, double height, float spread) {
        super(positionX, positionY, width, height);
        this.velocityX = (Math.random() * 50 - 25)*spread;
        this.velocityY = (Math.random() * 50 - 25)*spread;
        this.behaviors.add(new DestroyAfterTime(this, 2));
        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            return !(otherPhysicsObject instanceof SlashParticle || otherPhysicsObject instanceof Player);
        }, true));
        this.width = (Math.random()+1)*10;
        this.height = (Math.random()+1)*10;
        Color flameColors [] = {Color.RED, Color.ORANGE, Color.YELLOW};
        this.color = flameColors[(int)(Math.random()*3)];
    }
    private float timeToLive = 0;
    public SlashParticle(double positionX, double positionY, double width, double height, float initialVelocity, float timeToLive) {
        super(positionX, positionY, width, height);
        this.timeToLive = timeToLive;
        this.velocityX = (Math.random() * 100 - 50)*initialVelocity;
        this.velocityY = (Math.random() * 100 - 50)*initialVelocity;
        this.behaviors.add(new DestroyAfterTime(this, timeToLive));
        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            return !(otherPhysicsObject instanceof SlashParticle || otherPhysicsObject instanceof Player);
        }, true));
        this.width = (Math.random()+1)*4;
        this.height = (Math.random()+1)*4;
        this.color = new Color(255, 255, 255, 255);
    }
    private float livedTime = 0;
    @Override
    public void update() {
        super.update();
        this.velocityX -= 1*deltaTimeRender;
        this.velocityY -= 1*deltaTimeRender;
        this.livedTime += deltaTimeRender;
        if(timeToLive != 0){
            if((255*(this.timeToLive-this.livedTime)) > 0 && (255*(this.timeToLive-this.livedTime)) < 255){
                this.color = new Color(255, 255, 255,  Math.abs((int) (255*(this.timeToLive-this.livedTime))));
            }else{
                this.color = new Color(255, 255, 255,  0);
            }
        }
    }

}
