package src.components;

import java.util.List;

import src.Janela;
import src.behaviors.Behavior;
import src.behaviors.SpawnGameObject;
import src.components.inimigos.Inimigo1;
import src.components.inimigos.Inimigo2;

public class Spawner extends GameObject {
    private List<GameObject> objectsListBuffer;
    public PhysicsObject inimigo;
    public Behavior spawnBehavior;

    private List<GameObject> listaInimigos;

    public Spawner(int positionX, int positionY, int width, int height, List<GameObject> objectsListBuffer) {
        super(positionX, positionY, width, height);
        this.objectsListBuffer = objectsListBuffer;


        this.listaInimigos = new java.util.ArrayList<>();


        listaInimigos.add(new Inimigo1(positionX, positionY));
        listaInimigos.add(new Inimigo2(positionX, positionY));

        this.spawnBehavior = new SpawnGameObject((GameObject) this, this.objectsListBuffer, 2f, listaInimigos,
                (newInstance) -> {
                    newInstance.positionY = this.positionY;
                    ((PhysicsObject) newInstance).velocityX = Math.random() * 200 - 100;
                    ((PhysicsObject) newInstance).velocityY = -Math.random() * 100;
                    return null;
                });
        this.behaviors
                .add(this.spawnBehavior);
    }

    @Override
    public void update() {
        super.update();
        this.positionY = Janela.HEIGHT;
    }
    @Override
    public void draw(java.awt.Graphics2D g2d) {
    }
}
