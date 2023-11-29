package src.components;

import java.awt.*;

public class Button extends GameObject {
    private String name;
    public Button(String name, double x, double y, double w, double h){
        super(x, y, w, h);
        this.name = name;
    }    
    @Override
    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(50));
        g2d.fillRect((int) this.positionX,(int) (- this.positionY - this.height) ,(int) this.width,(int) this.height);
        
        
        int padding = 20;
        String texto = this.name;
        int textoWidth = g2d.getFontMetrics().stringWidth(texto);
        int textoHeight = g2d.getFontMetrics().getHeight();
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.blue);
        g2d.drawString(texto, (int) (this.positionX + textoWidth) , (int) (-this.positionY - this.height/2));
        g2d.setColor(Color.red);
    }
}