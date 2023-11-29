package src.components;

import java.awt.*;

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
