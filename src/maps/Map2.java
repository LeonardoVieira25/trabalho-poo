package src.maps;

import src.components.Button;
import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.components.GameObject;

import java.util.List;
import java.util.Random;

// E uma copia de Map0, mas com a mensagem "Voce perdeu" na tela

public class Map2 extends Maps {
    public Map2() { // e pra ser o mainMenu
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();
        Random random = new Random();

        int halfHeight = Janela.HEIGHT/2; // referente a tela
        int halfWidth = Janela.WIDTH/2; // referente a tela
        int espacamento = 100;

        List<String> frases = new java.util.ArrayList<>();
        frases.add("Foi quase, apesar do ser infinito. Quer jogar de novo?");
        frases.add("Perdeu. Quer tentar de novo?");
        frases.add("Não foi dessa vez. Tente de novo");
        frases.add("Game Over");
        frases.add("Try again");
        
        Button perdeu = new Button((String)frases.get(random.nextInt(frases.size())), halfWidth, halfHeight+200);
        System.out.println(perdeu.name);
        // `perdeu` eh apenas uma mensagem, nao tem ClickableArea()
        
        Button jogar = new Button("JOGAR", halfWidth, halfHeight);
        jogar.behaviors.add(new ClickableArea(jogar, () -> {
            System.out.println("Jogando");
            Scene.selectedMapId = 1;
        }));

        Button sair = new Button("QUIT", halfWidth, halfHeight - espacamento);
        jogar.behaviors.add(new ClickableArea(sair, () -> {
            System.out.println("Saindo");
            System.exit(0);
        }));
        
        objectsList.add(perdeu);
        objectsList.add(jogar);
        objectsList.add(sair);
        
    }
}
