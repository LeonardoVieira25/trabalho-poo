/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.components;

import java.awt.*;

import src.Scene;

public class Button extends GameObject {
    public String name;
    private boolean inicializado = false;
    private int centerX = 0;
    private int centerY = 0;

    private Color bordeColor = Color.black;
    private Color fillColor = Color.white;
    private Color textColor = Color.black;
    private Color bordeColorHovered = Color.white;
    private Color fillColorHovered = Color.black;
    private Color textColorHovered = Color.white;
    private int borderThinness = 5;

    private float particlesToSpawn = 50;

    private boolean lastHoverState = false;

    private java.util.List<GameObject> objectsList;
    public Button(String name, int x, int y) {
        super(0, 0, 10, 10);
        this.name = name;
        this.centerX = x;
        this.centerY = y;
    }
    public Button(String name, int x, int y, java.util.List<GameObject> objectsList) {
        super(0, 0, 10, 10);
        this.name = name;
        this.centerX = x;
        this.centerY = y;
        this.objectsList = objectsList;
    }

    @Override
    public void update() {
        super.update();
        if (objectsList != null && onHover != lastHoverState) {
            lastHoverState = onHover;
            if (lastHoverState) {
                for (int i = 0; i < particlesToSpawn; i++) {
                    PhysicsObject newInstance = new SlashParticle(Scene.mousePositionX, Scene.mousePositionY, 5, 5, 5, 0.5f);
                    objectsList.add(newInstance);
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));

        int padding = 10;
        String texto = this.name;
        int textoWidth = g2d.getFontMetrics().stringWidth(texto);
        int textoHeight = g2d.getFontMetrics().getHeight();

        if (!inicializado) {
            inicializado = true;
            super.width = textoWidth + padding * 2;
            super.height = textoHeight + padding * 2;
            super.positionX = centerX - super.width / 2;
            super.positionY = centerY - super.height / 2;
        }
        if (onHover) {
            g2d.setColor(fillColorHovered);
            g2d.setPaint(bordeColorHovered);
            g2d.fillRect((int) (this.positionX - borderThinness), (int) (-this.positionY - height - borderThinness),
                    (int) width + borderThinness * 2, (int) height + borderThinness * 2);
            g2d.setPaint(fillColorHovered);
            g2d.fillRect((int) (this.positionX), (int) (-this.positionY - height), (int) width, (int) height);
            g2d.setColor(textColorHovered);
            g2d.drawString(texto, (int) (this.positionX + padding),
                    (int) (-this.positionY - height + textoHeight + padding - 5));
        } else {
            g2d.setColor(fillColor);
            g2d.setPaint(bordeColor);
            g2d.fillRect((int) (this.positionX - borderThinness), (int) (-this.positionY - height - borderThinness),
                    (int) width + borderThinness * 2, (int) height + borderThinness * 2);
            g2d.setPaint(fillColor);
            g2d.fillRect((int) (this.positionX), (int) (-this.positionY - height), (int) width, (int) height);
            g2d.setColor(textColor);
            g2d.drawString(texto, (int) (this.positionX + padding),
                    (int) (-this.positionY - height + textoHeight + padding - 5));
        }

    }
}