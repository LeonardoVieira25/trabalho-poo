package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import java.awt.event.MouseEvent;

import src.components.GameObject;
import src.maps.Maps;

import src.Scene;

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
                int FPS = 30;

                while (true) {
                    firstTime = System.nanoTime();

                    for (GameObject gameObject : maps.get(selectedMapId).objectsList) {
                        if (gameObject.removeNextIteration) {
                            ObjectsToRemove.add(gameObject);
                        } else {
                            gameObject.update();
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
                int FPS = 1;
                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // System.out.println("render");
                        janela.render(new ArrayList<GameObject>(maps.get(selectedMapId).objectsList));
                    }
                };
                new Timer(20 , taskPerformer).start();
            }
        };
        Thread renderThread = new Thread(renderLoop);
        renderThread.start();
        Thread gameThread = new Thread(gameLoop);
        gameThread.start();
    }
}
