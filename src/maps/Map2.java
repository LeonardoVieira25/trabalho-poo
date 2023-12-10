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
import src.utils.XmlLoader;

import java.util.List;
import java.util.Random;


public class Map2 extends Maps {
    List<Button> raking = new java.util.ArrayList<Button>();
    int halfHeight = Janela.HEIGHT / 2; // referente a tela
    int halfWidth = Janela.WIDTH / 2; // referente a tela
    int espacamento = 100;

    public Map2() throws Exception { // e pra ser o mainMenu
        objectsList = new java.util.ArrayList<GameObject>();
        objectsListBuffer = new java.util.ArrayList<GameObject>();
        Random random = new Random();

        List<String> frases = new java.util.ArrayList<>();
        frases.add("Foi quase, apesar do ser infinito. Quer jogar de novo?");
        frases.add("Perdeu. Quer tentar de novo?");
        frases.add("Não foi dessa vez. Tente de novo");
        frases.add("Game Over");
        frases.add("Try again");

        Button perdeu = new Button((String) frases.get(random.nextInt(frases.size())), halfWidth,
                halfHeight + 4 * 2 * espacamento / 3);

        // `perdeu` eh apenas uma mensagem, nao tem ClickableArea()

        Button jogar = new Button("Jogar novamente", halfWidth, halfHeight + 3 * 2 * espacamento / 3);
        jogar.behaviors.add(new ClickableArea(jogar, () -> {
            Scene.selectedMapId = 0;
            Maps.isPaused = false;
        }));

        Button sair = new Button("QUIT", halfWidth, halfHeight + 2 * 2 * espacamento / 3);
        jogar.behaviors.add(new ClickableArea(sair, () -> {
            System.out.println("Saindo");
            System.exit(0);
        }));

        Button ranking = new Button("          RANKING          ", halfWidth, halfHeight);
        objectsList.add(ranking);

        List<String> topPlayers = XmlLoader.getPlayersRankings();
        for (int i = 0; i < topPlayers.size(); i++) {
            Button topPlayer = new Button(topPlayers.get(i), halfWidth, halfHeight - 2 * (i + 1) * espacamento / 3);
            raking.add(topPlayer);
            objectsList.add(topPlayer);
        }

        objectsList.add(perdeu);
        objectsList.add(jogar);
        objectsList.add(sair);

    }

    @Override
    public void onEnter() {
        super.onEnter();
        for (Button button : raking) {
            button.removeNextIteration = true;
        }
        List<String> topPlayers = XmlLoader.getPlayersRankings();
        for (int i = 0; i < topPlayers.size(); i++) {
            Button topPlayer = new Button(topPlayers.get(i), halfWidth, halfHeight - 2 * (i + 1) * espacamento / 3);
            raking.add(topPlayer);
            objectsList.add(topPlayer);
        }
    }
}
