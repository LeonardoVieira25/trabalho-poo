package src.components;

import java.awt.*;

public class Contador extends GameObject{
    private String texto;
    private String baseText;
    private String id;

    private Color bordeColor = Color.black;
    private Color fillColor = Color.white;
    private Color textColor = Color.black;
    private int borderThinness = 5;


    private int centerX, centerY;
    private boolean inicializado = false;

    public Contador(String baseText, String id, int x, int y){
        super(0,0,0,0);
        this.baseText = baseText;
        this.centerX = x;
        this.centerY = y;
        this.id = id;
    }
    public void updateValue(String str){
        this.texto = baseText + str;
    }
    public String getId() {
        return id;
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));

        int padding = 10;
        int textoWidth = g2d.getFontMetrics().stringWidth(texto);
        int textoHeight = g2d.getFontMetrics().getHeight();
        
        if(!inicializado){
            inicializado = true;
            super.width = textoWidth + padding*2;
            super.height = textoHeight + padding*2;
            super.positionX = centerX - super.width/2;
            super.positionY = centerY - super.height/2;
        }
        g2d.setColor(fillColor);
        g2d.setPaint(bordeColor);
        g2d.fillRect((int)(this.positionX-borderThinness), (int) (- this.positionY - height-borderThinness) ,(int) width+borderThinness*2,(int) height+borderThinness*2);
        g2d.setPaint(fillColor);
        g2d.fillRect((int)(this.positionX), (int) (- this.positionY - height) ,(int) width,(int) height);
        g2d.setColor(textColor);
        g2d.drawString(texto, (int)(this.positionX + padding) , (int) (- this.positionY - height + textoHeight + padding - 5));
        
    }
}
