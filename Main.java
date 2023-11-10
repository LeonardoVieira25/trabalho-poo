import src.GameObject;
import src.Janela;
import src.Scene;
import src.maps.Map1;

public class Main {
    public static void main(String[] args) {
        Janela janela = new Janela();
        
        // Scene scene = new Scene(janela);
        Map1 mapa = new Map1(janela);
        // scene.janela.renderingList.add(new GameObject(0, 500, 1000, 20));

        
        
        // List<GameObject> updateList = new java.util.ArrayList<GameObject>();
        // PhysicsObject cubo = new PhysicsObject(450, 100, 100, 100);
        // GameObject chao = new GameObject(0, 500, 1000, 20);
        // GameObject inimigo = new GameObject(900, 400, 100, 100);
        // cubo.forceY = (300);
        // cubo.velocityX = (300);
        // janela.renderingList.add(cubo);
        // janela.renderingList.add(chao);
        // janela.renderingList.add(inimigo);
        // updateList.add(cubo);
        // updateList.add(chao);
        // updateList.add(inimigo);


        // Runnable gameLoop = new Runnable() {
        //     @Override
        //     public void run() {
        //         double firstTime, lastTime, waitTime;
        //         int FPS = 24;

        //         while (true) {
        //             firstTime = System.nanoTime();
        //             // if (cubo.positionY + cubo.height > chao.positionY) {
        //             //     if (Math.signum(cubo.velocityY) == 1) { // esta caindo
        //             //         cubo.velocityY = -Math.abs(cubo.velocityY) * 0.8;
        //             //         cubo.velocityX = -cubo.velocityX * 0.8;
        //             //     }
        //             // }
        //             // inimigo.positionX = inimigo.positionX - inimigo.deltaTimeRender * 100;
        //             // if (inimigo.positionX + inimigo.width > cubo.positionX &&
        //             //         inimigo.positionX < cubo.positionX + cubo.width &&
        //             //         inimigo.positionY + inimigo.height > cubo.positionY &&
        //             //         inimigo.positionY < cubo.positionY + cubo.height) {
        //             //     System.out.println("colidiu");
        //             // }
        //             for (GameObject gameObject : updateList) {
        //                 gameObject.update();
        //             }
        //             janela.render();
        //             lastTime = System.nanoTime();
        //             try {
        //                 waitTime = ((1_000_000_000.0 / FPS) - (long) (lastTime - firstTime));
        //                 Thread.sleep(waitTime < 0L ? 0L : (long) (waitTime / 1_000_000.0));
        //             } catch (InterruptedException e) {
        //             }
        //         }
        //     }
        // };
        // Thread gameThread = new Thread(gameLoop);
        // gameThread.start();
    }
}