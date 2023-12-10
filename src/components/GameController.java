/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.components;

import java.util.List;

import src.Janela;
import src.Scene;
import src.behaviors.ClickableArea;
import src.maps.Maps;

public class GameController extends GameObject {
    private final int pointsForLevelUp = 30;
    private int currentLevel = 1;
    private int pontos = 0;

    public GameController(List<GameObject> objectsList) {
        super(0, 0, 0, 0);

        List<GameObject> upgradeScreen = new java.util.ArrayList<GameObject>();
        Button upgradeVidaInicial = new Button("Vida Inicial +1", Janela.WIDTH / 2 - 200, Janela.HEIGHT / 2,
                objectsList);
        upgradeVidaInicial.behaviors.add(new ClickableArea(upgradeVidaInicial, () -> {
            Maps.eventManager.trigger("UpgradeVidaInicial:+1");
        }));
        upgradeScreen.add(upgradeVidaInicial);

        Button spawnRate = new Button("Spawn Rate +1", Janela.WIDTH / 2, Janela.HEIGHT / 2, objectsList);
        spawnRate.behaviors.add(new ClickableArea(spawnRate, () -> {
            Maps.eventManager.trigger("UpgradeSpawnRate:+1");
        }));
        upgradeScreen.add(spawnRate);

        Button spread = new Button("Spread +1", Janela.WIDTH / 2 + 200, Janela.HEIGHT / 2, objectsList);
        spread.behaviors.add(new ClickableArea(spread, () -> {
            Maps.eventManager.trigger("UpgradeSpread:+1");
        }));
        upgradeScreen.add(spread);


        listener = (event) -> {
            if (event instanceof String) {
                String eventString = (String) event;
                if (eventString.startsWith("Pontos")) {
                    String[] eventStringSplit = eventString.split(":");
                    if (eventStringSplit[1].startsWith("+")) {
                        this.pontos += Integer.parseInt(eventStringSplit[1].substring(1));
                    } else if (eventStringSplit[1].startsWith("-")) {
                        this.pontos -= Integer.parseInt(eventStringSplit[1].substring(1));
                    } else {
                        this.pontos = Integer.parseInt(eventStringSplit[1]);
                    }

                    if (pontos - (currentLevel - 1) * pointsForLevelUp >= pointsForLevelUp) {
                        Maps.isPaused = true;
                        for (GameObject component : upgradeScreen) {
                            component.removeNextIteration = false;
                            objectsList.add(component);
                        }
                        currentLevel++;
                        Maps.eventManager.trigger("Nivel:" + currentLevel);
                    }
                }

                if (eventString.startsWith("Upgrade")) {
                    Maps.isPaused = false;
                    for (GameObject component : upgradeScreen) {
                        component.removeNextIteration = true;
                    }
                }
                if (eventString.startsWith("GameOver")) {
                    Maps.isPaused = true;
                    Button gameOver = new Button("Game Over", Janela.WIDTH / 2, Janela.HEIGHT / 2, objectsList);
                    gameOver.behaviors.add(new ClickableArea(gameOver, () -> {
                        Maps.isPaused = false;
                        Scene.selectedMapId = 2;
                    }));
                    objectsList.add(gameOver);
                }

            }
        };
    }

    public void update() {
    }

    public void draw(java.awt.Graphics2D g2d) {
    }
}
