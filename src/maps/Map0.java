/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.maps;

import src.components.Button;
import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;


public class Map0 extends Maps {
    public Map0() throws Exception { // e pra ser o mainMenu
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();

        int halfHeight = Janela.HEIGHT/2; // referente a tela
        int halfWidth = Janela.WIDTH/2; // referente a tela
        int espacamento = 100;
        
        Button jogar = new Button("JOGAR", halfWidth, halfHeight, objectsListBuffer);
        jogar.behaviors.add(new ClickableArea(jogar, () -> {
            System.out.println("Jogando");
            Scene.selectedMapId = 1;
        }));

        Button sair = new Button("Sair do jogo", halfWidth, halfHeight - espacamento, objectsListBuffer);
        jogar.behaviors.add(new ClickableArea(sair, () -> {
            System.out.println("Saindo");
            System.exit(0);
        }));

        objectsList.add(jogar);
        objectsList.add(sair);
    }
}
