import src.GameObject;
import src.PhysicsObject;

public class Main {
    public static void main(String[] args) {
        Janela janela = new Janela();
        PhysicsObject cubo = new PhysicsObject(100, 100, 100, 100);
        GameObject chao = new GameObject(0, 500, 1000, 20);
        cubo.forceY = 10;
        janela.renderingList.add(cubo);
        janela.renderingList.add(chao);

        while (true) {
            cubo.update();
            if (cubo.positionY > chao.positionY - cubo.height) {
                cubo.velocityY = -cubo.velocityY*0.8;
            }
            janela.render();
        }
    }
}
