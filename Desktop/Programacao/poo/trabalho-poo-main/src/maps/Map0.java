package src.maps;

import src.components.Button;
import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;



public class Map0 extends Maps {
    public Map0() { // e pra ser o mainMenu
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();
        float halfHeight = Janela.HEIGHT/2; // referente a tela
        float halfWidth = Janela.WIDTH/2; // referente a tela
        float padW = 100; // padding width
        float padH = 50; // padding height
        float espacamento = 40;
        
        Button jogar = new Button("JOGAR", halfWidth-"JOGAR".length()*35/2, halfHeight, (halfWidth-(halfWidth-"JOGAR".length()*35/2))*2, padH);
        jogar.behaviors.add(new ClickableArea(jogar, () -> {
            System.out.println("Jogando");
            Scene.selectedMapId = 1;
        }));

        Button sair = new Button("QUIT", halfWidth-"SAIR".length()*35/2, halfHeight - espacamento - padH, (halfWidth-(halfWidth-"SAIR".length()*35/2))*2, padH);
        jogar.behaviors.add(new ClickableArea(sair, () -> {
            System.out.println("Saindo");
            System.exit(0);
        }));
       
        objectsList.add(jogar);
        objectsList.add(sair);
        
    }
}
