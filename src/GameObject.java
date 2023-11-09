package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public double positionX;
    public double positionY;
    public double width;
    public double height;
    public String name;

    public List<GameObject> intersectList = new ArrayList<GameObject>();


    private double lastTimeRender = System.nanoTime();
    public double deltaTimeRender = 0;

    public GameObject(double positionX, double positionY, double width, double height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }
    public void update() {
        long currentTimeNano = System.nanoTime();
        this.deltaTimeRender = (currentTimeNano - lastTimeRender)/1_000_000_000;
        lastTimeRender = currentTimeNano;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(50));
        g2d.fillRect((int) this.positionX,(int) this.positionY,(int) this.width,(int) this.height);
    }
}