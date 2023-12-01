package src.components;

import java.awt.*;

import src.behaviors.Behavior;

public class PhysicsObject extends GameObject {
    public double velocityX = 0;
    public double forceX = 0;
    public double velocityY = 0;
    public double forceY = 0;
    public float drag = 0.5f;
    public PhysicsObject(double positionX, double positionY, double width, double height){
        super(positionX, positionY, width, height);
    }
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
    @Override
    public void update() {
        
        this.velocityX = this.velocityX + this.forceX*this.deltaTimeRender;
        this.positionX = this.positionX + this.velocityX*this.deltaTimeRender;
        
        this.velocityY = this.velocityY + this.forceY*this.deltaTimeRender;
        this.positionY = this.positionY + this.velocityY*this.deltaTimeRender;

        this.velocityX = this.velocityX + Math.signum(this.velocityX)*-1*this.drag*this.deltaTimeRender;
        this.velocityY = this.velocityY + Math.signum(this.velocityY)*-1*this.drag*this.deltaTimeRender;

        super.update();
    }
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}
