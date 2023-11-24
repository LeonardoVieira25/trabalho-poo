package src;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

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
                    janela.render(maps.get(selectedMapId).objectsList);
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
        Thread gameThread = new Thread(gameLoop);
        gameThread.start();
    }
}
