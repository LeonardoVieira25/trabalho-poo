package src;

import java.util.List;

public class Scene {
    public List<GameObject> updateList = new java.util.ArrayList<GameObject>();

    public Janela janela;

    public Scene(Janela janela) {
        this.janela = janela;

        Runnable gameLoop = new Runnable() {
            @Override
            public void run() {
                double firstTime, lastTime, waitTime;
                int FPS = 24;

                while (true) {
                    firstTime = System.nanoTime();                    
                    for (GameObject gameObject : updateList) {
                        gameObject.update();
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
