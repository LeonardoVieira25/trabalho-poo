package src.maps;


import src.GameObject;
import src.Janela;
import src.PhysicsObject;
import src.Scene;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Map1 extends Scene {
    public Map1(Janela janela) {
        super(janela);
        janela.renderingList.add(new GameObject(0, 500, 1000, 20));
        janela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PhysicsObject novoObjeto = new PhysicsObject(e.getX(), e.getY(), 100, 100);
                novoObjeto.forceY = (300);
                novoObjeto.velocityX = (800);
                janela.renderingList.add(novoObjeto);
                updateList.add(novoObjeto);
            }
        });
    }
}
