package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import src.components.GameObject;
import src.maps.Maps;

public class Scene {

    public List<Maps> maps;
    public static int selectedMapId = 0;

    public static int mousePressedX = -1;
    public static int mousePressedY = -1;

    public static int mousePositionX = -1;
    public static int mousePositionY = -1;

    public Janela janela;
    
    

    public Scene(Janela janela, List<Maps> maps) {
        this.janela = janela;
        this.maps = maps;

        this.janela.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = -(e.getY() - Janela.HEIGHT);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressedX = -1;
                mousePressedY = -1;
                super.mouseReleased(e);
            }

        });
        this.janela.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePositionX = e.getX();
                mousePositionY = -(e.getY() - Janela.HEIGHT);
                super.mouseMoved(e);
            }
        });
        
        run();

    }
    
    private void run() {
        Runnable gameLoop = new Runnable() {
            @Override
            public void run() {
                List<GameObject> ObjectsToRemove = new ArrayList<GameObject>();
                double firstTime, lastTime, waitTime;
                int FPS = 60;

                while (true) {
                    firstTime = System.nanoTime();

                    for (GameObject gameObject : maps.get(selectedMapId).objectsList) {
                        if (gameObject.removeNextIteration) {
                            ObjectsToRemove.add(gameObject);
                        } else {
                            gameObject.update();
                        }
                    }
                    // * verificar por colisões */
                    List<GameObject> objectsList = maps.get(selectedMapId).objectsList;
                    for (int i = 0; i < objectsList.size(); i++) {
                        GameObject gameObject = objectsList.get(i);
                        for (int j = i + 1; j < objectsList.size(); j++) {
                            GameObject otherGameObject = objectsList.get(j);
                            if (gameObject != otherGameObject &&
                            // * verifica se ambos são physics objects */
                                    gameObject instanceof src.components.PhysicsObject &&
                                    otherGameObject instanceof src.components.PhysicsObject &&

                            // * verifica se ambos tem o comportamento de colisão */
                                    gameObject.behaviors.stream()
                                            .anyMatch(behavior -> behavior instanceof src.behaviors.Collision)
                                    &&
                                    otherGameObject.behaviors.stream()
                                            .anyMatch(behavior -> behavior instanceof src.behaviors.Collision)) {

                                // * seleciona a behavior de colisão dos objetos */
                                src.behaviors.Collision collisionGameObject = (src.behaviors.Collision) gameObject.behaviors
                                        .stream()
                                        .filter(behavior -> behavior instanceof src.behaviors.Collision)
                                        .findFirst()
                                        .orElse(null);

                                src.behaviors.Collision collisionOtherGameObject = (src.behaviors.Collision) otherGameObject.behaviors
                                        .stream()
                                        .filter(behavior -> behavior instanceof src.behaviors.Collision)
                                        .findFirst()
                                        .orElse(null);

                                if (
                                // * verifica se os objetos estão colidindo */
                                gameObject.positionX < otherGameObject.positionX + otherGameObject.width &&
                                        gameObject.positionX + gameObject.width > otherGameObject.positionX &&
                                        gameObject.positionY < otherGameObject.positionY + otherGameObject.height &&
                                        gameObject.positionY + gameObject.height > otherGameObject.positionY) {
                                    collisionGameObject
                                            .collisionEntered((src.components.PhysicsObject) otherGameObject);
                                    collisionOtherGameObject
                                            .collisionEntered((src.components.PhysicsObject) gameObject);
                                }

                            }
                        }
                    }

                    // janela.render(maps.get(selectedMapId).objectsList);
                    if (ObjectsToRemove.size() > 0) {
                        maps.get(selectedMapId).objectsList.removeAll(ObjectsToRemove);
                        ObjectsToRemove.clear();
                    }
                    if (maps.get(selectedMapId).objectsListBuffer.size() > 0) {
                        maps.get(selectedMapId).objectsList.addAll(maps.get(selectedMapId).objectsListBuffer);
                        maps.get(selectedMapId).objectsListBuffer.clear();
                    }
                    lastTime = System.nanoTime();
                    try {
                        waitTime = ((1_000_000_000.0 / FPS) - (long) (lastTime - firstTime));
                        if(waitTime < 0L){
                            System.out.println("fps máximo atingido");
                        }
                        Thread.sleep(waitTime < 0L ? 0L : (long) (waitTime / 1_000_000.0));
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        Runnable renderLoop = new Runnable() {
            @Override
            public void run() {

                // double firstTime, lastTime, waitTime = 0;
                // int FPS = 1;
                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // System.out.println("render");
                        janela.render(new ArrayList<GameObject>(maps.get(selectedMapId).objectsList));
                    }
                };
                new Timer(20, taskPerformer).start();
            }
        };
        Thread renderThread = new Thread(renderLoop);
        renderThread.start();
        Thread gameThread = new Thread(gameLoop);
        gameThread.start();
    }

    
}
