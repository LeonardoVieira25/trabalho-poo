/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.components;

import java.util.List;

import src.Janela;
import src.behaviors.Behavior;
import src.behaviors.SpawnGameObject;
import src.components.inimigos.Inimigo1;
import src.components.inimigos.Inimigo2;
import src.components.inimigos.Inimigo3;
import src.components.inimigos.Inimigo4;
import src.maps.Maps;
import src.utils.XmlLoader;

public class Spawner extends GameObject {
    private List<GameObject> objectsListBuffer;
    public PhysicsObject inimigo;
    public Behavior spawnBehavior;

    private List<GameObject> listaInimigos;
    private List<GameObject> newListaInimigos;

    public Spawner(int positionX, int positionY, int width, int height, List<GameObject> objectsListBuffer) {
        super(positionX, positionY, width, height);
        this.objectsListBuffer = objectsListBuffer;


        this.listaInimigos = new java.util.ArrayList<>();
        listener = (e) -> {
            if(e instanceof String){
                String eventString = (String) e;
                if(eventString.startsWith("Nivel:")){
                    int currentLevel = Integer.parseInt(eventString.split(":")[1]);
                    String enemyTypes = XmlLoader.getEnemyTypes(currentLevel);


                    if(enemyTypes.indexOf("1") != -1){
                        listaInimigos.add(new Inimigo1(positionX, positionY));
                    }
                    if(enemyTypes.indexOf("2") != -1){
                        listaInimigos.add(new Inimigo2(positionX, positionY));
                    }
                    if(enemyTypes.indexOf("3") != -1){
                        listaInimigos.add(new Inimigo3(positionX, positionY));
                    }
                    if(enemyTypes.indexOf("4") != -1){
                        listaInimigos.add(new Inimigo4(positionX, positionY));
                    }

                }
                
            }
        };



        this.spawnBehavior = new SpawnGameObject((GameObject) this, this.objectsListBuffer, 2f, listaInimigos,
                (newInstance) -> {
                    newInstance.positionY = this.positionY;
                    ((PhysicsObject) newInstance).velocityX = Math.random() * 200 - 100;
                    ((PhysicsObject) newInstance).velocityY = -Math.random() * 100;
                    return null;
                });
        this.behaviors.add(this.spawnBehavior);
        
        
    }
    
    @Override
    public void update() {
        if(Maps.isPaused){
            return;
        }
        super.update();
        this.positionY = Janela.HEIGHT;
        if(newListaInimigos != null){
            this.listaInimigos = newListaInimigos;
            this.behaviors.remove(this.spawnBehavior);
            this.spawnBehavior = new SpawnGameObject((GameObject) this, this.objectsListBuffer, 2f, listaInimigos,
                (newInstance) -> {
                    newInstance.positionY = this.positionY;
                    ((PhysicsObject) newInstance).velocityX = Math.random() * 200 - 100;
                    ((PhysicsObject) newInstance).velocityY = -Math.random() * 100;
                    return null;
                });
        }
    }
    @Override
    public void draw(java.awt.Graphics2D g2d) {
    }
}
