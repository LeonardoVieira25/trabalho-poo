package src.maps;

import src.components.Button;
import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;
import src.utils.XmlLoader;


public class Map0 extends Maps {
    public Map0() { // e pra ser o mainMenu
        XmlLoader.loadLevels();


        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();
        float halfHeight = Janela.HEIGHT/2; // referente a tela
        float halfWidth = Janela.WIDTH/2; // referente a tela
        float optW = 150; // option width
        float optH = 50; // option height
        float espacamento = 20;
        Button jogar = new Button("JOGAR", halfWidth - optW/2 , halfHeight, optW, optH);
        jogar.behaviors.add(new ClickableArea(jogar, () -> {
            System.out.println("Clicou 0");
            Scene.selectedMapId = 1;
        }));

        Button sair = new Button("QUIT", halfWidth - optW/2, halfHeight - espacamento - optH - espacamento, optW, optH);
        jogar.behaviors.add(new ClickableArea(sair, () -> {
            System.out.println("Clicou SAIR");
            System.exit(0);
        }));

        objectsList.add(jogar);
        objectsList.add(sair);
        
    }
}
