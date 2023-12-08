package src.components.inimigos;

import java.awt.Color;
import java.awt.Graphics2D;

import src.behaviors.Behavior;
import src.behaviors.BounceHorizontal;
import src.behaviors.BounceVertical;
import src.behaviors.Collision;
import src.components.PhysicsObject;
import src.components.SlashParticle;
import src.maps.Maps;

public class Inimigo1 extends Inimigo {
    private static int width = 20;
    private static int height = 20;
    
    @Override
    public Object clone() {
        Inimigo1 clone = new Inimigo1(positionX, positionY);
        clone.velocityX = this.velocityX;
        clone.forceX = this.forceX;
        clone.velocityY = this.velocityY;
        clone.forceY = this.forceY;
        clone.drag = this.drag;
        clone.color = this.color;
        
        for (Behavior behavior : this.behaviors) {
            Behavior cloneBehavior = behavior.clone();
            cloneBehavior.gameObject = clone;
            
            clone.behaviors.add(cloneBehavior);
        }
        return clone;
    }
    public Inimigo1(double positionX, double positionY) {
        super(positionX, positionY, width, height);
        this.behaviors.add(new BounceVertical(this, () -> {
            Maps.eventManager.trigger("Vida:-1");
        }));
        this.behaviors.add(new BounceHorizontal(this));
        this.forceY = -50;
        this.forceX = 0;
        
        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            if(otherPhysicsObject instanceof SlashParticle){
                Maps.eventManager.trigger("pontos:+1");
            }
            return otherPhysicsObject instanceof SlashParticle;
        }));
    }
    @Override
    public void draw(java.awt.Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillOval((int) this.positionX, (int) (-this.positionY - Inimigo1.height), (int) Inimigo1.width, (int) Inimigo1.height);
    }
}
