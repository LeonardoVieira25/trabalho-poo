package src;

import java.util.ArrayList;
import java.util.List;

import src.behaviors.Behavior;
import src.components.GameObject;

public class Scene {
    public List<GameObject> updateList = new java.util.ArrayList<GameObject>();
    public List<GameObject> updateListBuffer = new java.util.ArrayList<GameObject>();

    public static int mousePressedX = -1;
    public static int mousePressedY = -1;

    public static int mousePositionX = -1;
    public static int mousePositionY = -1;

    public Janela janela;

    public Scene(Janela janela) {
        this.janela = janela;

        Runnable gameLoop = new Runnable() {
            @Override
            public void run() {
                List<GameObject> ObjectsToRemove = new ArrayList<GameObject>();
                double firstTime, lastTime, waitTime;
                int FPS = 60;

                while (true) {
                    firstTime = System.nanoTime();
                    
                    for (GameObject gameObject : updateList) {
                        if(gameObject.removeNextIteration){
                            ObjectsToRemove.add(gameObject);
                        }else{
                            gameObject.update();
                        }
                    }
                    if(ObjectsToRemove.size() > 0){
                        updateList.removeAll(ObjectsToRemove);
                        ObjectsToRemove.clear();
                    }
                    janela.render();
                    lastTime = System.nanoTime();
                    try {
                        waitTime = ((1_000_000_000.0 / FPS) - (long) (lastTime - firstTime));
                        Thread.sleep(waitTime < 0L ? 0L : (long) (waitTime / 1_000_000.0));
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        Thread gameThread = new Thread(gameLoop);
        gameThread.start();
    }
}
