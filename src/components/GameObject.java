package src.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import src.behaviors.Behavior;

public class GameObject implements Cloneable {
    public double positionX;
    public double positionY;
    public double width;
    public double height;
    public String name;
    public Color color = Color.gray;
    public boolean removeNextIteration = false;
    public boolean onHover = false;

    public List<Behavior> behaviors = new ArrayList<Behavior>();

    private double lastTimeRender = System.nanoTime();
    public double deltaTimeRender = 0;

    @Override
    public Object clone() {
        GameObject clone = new GameObject(positionX, positionY, width, height);
        for (Behavior behavior : this.behaviors) {
            Behavior cloneBehavior = behavior.clone();
            cloneBehavior.gameObject = clone;

            clone.behaviors.add(cloneBehavior);
        }
        return clone;
    }

    public void resetDeltaTimeRender() {
        this.lastTimeRender = System.nanoTime();
    }

    public GameObject(double positionX, double positionY, double width, double height) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public void update() {
        long currentTimeNano = System.nanoTime();

        for (Behavior behavior : behaviors) {
            behavior.update();
        }

        this.deltaTimeRender = (currentTimeNano - lastTimeRender) / 1_000_000_000;
        lastTimeRender = currentTimeNano;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(50));
        g2d.fillRect((int) this.positionX, (int) (-this.positionY - this.height), (int) this.width, (int) this.height);
    }
}