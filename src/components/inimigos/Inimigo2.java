package src.components.inimigos;

import java.awt.Color;
import java.awt.Image;
import java.io.File;

import src.behaviors.Behavior;
import src.behaviors.BounceHorizontal;
import src.behaviors.BounceVertical;
import src.behaviors.Collision;
import src.components.SlashParticle;
import src.maps.Maps;

public class Inimigo2 extends Inimigo {
    private static int width = 100;
    private static int height = 100;
    private Image image;

    @Override
    public Object clone() {
        Inimigo2 clone = new Inimigo2(positionX, positionY);
        clone.velocityX = this.velocityX;
        clone.forceX = this.forceX;
        clone.velocityY = this.velocityY;
        clone.forceY = this.forceY;
        clone.drag = this.drag;
        clone.color = this.color;
        clone.image = this.image;

        for (Behavior behavior : this.behaviors) {
            Behavior cloneBehavior = behavior.clone();
            cloneBehavior.gameObject = clone;

            clone.behaviors.add(cloneBehavior);
        }
        return clone;
    }

    public Inimigo2(double positionX, double positionY) {
        super(positionX, positionY, width, height);
        this.behaviors.add(new BounceVertical(this, () -> {
            Maps.eventManager.trigger("Vida:-1");
        }));
        this.color = Color.RED;
        this.behaviors.add(new BounceHorizontal(this));
        this.forceY = -10;
        this.forceX = 0;

        this.behaviors.add(new Collision(this, (otherPhysicsObject) -> {
            if (otherPhysicsObject instanceof SlashParticle) {
                Maps.eventManager.trigger("pontos:+1");
            }
            return otherPhysicsObject instanceof SlashParticle;
        }));

        this.image = new File("src/assets/inimigo2.png").getAbsoluteFile().toURI().toString().contains("jar")
                ? new javax.swing.ImageIcon(getClass().getResource("/assets/inimigo2.png")).getImage()
                : new javax.swing.ImageIcon("src/assets/inimigo2.png").getImage();
    }

    @Override
    public void draw(java.awt.Graphics2D g2d) {
        // g2d.setColor(Color.RED);
        g2d.drawImage(this.image, (int) this.positionX, (int) (-this.positionY - Inimigo2.height), Inimigo2.width,
                Inimigo2.height, null);
    }
}
