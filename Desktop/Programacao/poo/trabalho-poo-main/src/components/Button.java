package src.components;

import java.awt.*;
import java.awt.Color;


public class Button extends GameObject {
    private String name;
    public Button(String name, double x, double y, double w, double h){
        super(x, y, w, h);
        this.name = name;
    }    
    @Override
    public void draw(Graphics2D g2d){
        int padding = 10;
        String texto = this.name;
        // int textoWidth = g2d.getFontMetrics().stringWidth(texto);
        // int textoHeight = g2d.getFontMetrics().getHeight();
        int textoWidth = texto.length()*10;
        int textoHeight = 50;

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(50));
        g2d.fillRect((int)(this.positionX), (int) (- this.positionY - textoHeight) ,(int) this.width,(int) this.height);
        
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString(texto, (int) (this.positionX + this.width/2 - textoWidth/2 - padding) , (int) (-this.positionY - this.height/2));
        g2d.setColor(Color.red);
    }
}