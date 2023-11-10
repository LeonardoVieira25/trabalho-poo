package src;

import java.awt.Graphics;

public class PhysicsObject extends GameObject {
    public double velocityX = 0;
    public double forceX = 0;
    public double velocityY = 0;
    public double forceY = 0;
    public PhysicsObject(double positionX, double positionY, double width, double height){
        super(positionX, positionY, width, height);
    }
    @Override
    public void update() {
        
        this.velocityX = this.velocityX + this.forceX*this.deltaTimeRender;
        this.positionX = this.positionX + this.velocityX*this.deltaTimeRender;
        
        this.velocityY = this.velocityY + this.forceY*this.deltaTimeRender;
        this.positionY = this.positionY + this.velocityY*this.deltaTimeRender;
        super.update();
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        
    }
}
