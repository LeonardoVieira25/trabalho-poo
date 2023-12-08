package src.components;

import java.util.List;

import src.Janela;
import src.behaviors.Behavior;
import src.behaviors.SpawnGameObject;

public class Spawner extends GameObject {
    private List<GameObject> objectsListBuffer;
    public PhysicsObject inimigo;
    public Behavior spawnBehavior;

    public Spawner(int positionX, int positionY, int width, int height, List<GameObject> objectsListBuffer) {
        super(positionX, positionY, width, height);
        this.objectsListBuffer = objectsListBuffer;

        // inimigo = new PhysicsObject(this.positionX, this.positionY, 20, 20);
        // inimigo.behaviors.add(new BounceVertical(inimigo));
        // inimigo.behaviors.add(new BounceHorizontal(inimigo));
        // inimigo.forceY = -50;
        // inimigo.forceX = 0;

        // inimigo.behaviors.add(new Collision(inimigo, (otherPhysicsObject) -> {
        // return otherPhysicsObject instanceof SlashParticle;
        // }));
        this.spawnBehavior = new SpawnGameObject((GameObject) this, this.objectsListBuffer, 0.5f, inimigo,
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
        // this.positionX = Janela.WIDTH / 2;
    }

}
