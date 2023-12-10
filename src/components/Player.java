/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.components;

import java.util.List;

import src.Scene;
import src.maps.Maps;
import src.utils.XmlLoader;

public class Player extends PhysicsObject {
    private int id;
    private String username;
    private String password;
    private int vidaInicial;
    private int maxPontuacao;
    private float spawnRate;
    private float spread;

    private int pontuacaoAtual = 0;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int getMaxPontuacao() {
        return maxPontuacao;
    }

    public float getSpawnRate() {
        return spawnRate;
    }

    public float getSpread() {
        return spread;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVidaInicial(int vidaInicial) {
        this.vidaInicial = vidaInicial;
    }

    public void setMaxPontuacao(int maxPontuacao) {
        this.maxPontuacao = maxPontuacao;
    }

    public void setSpawnRate(float spawnRate) {
        this.spawnRate = spawnRate;
    }

    public void setSpread(float spread) {
        this.spread = spread;
    }

    private List<GameObject> objectsList;
    private int lastPositionX = 0;
    private int lastPositionY = 0;
    private int vida = 100;

    private void onFirstUpdate() {
        Maps.eventManager.trigger("Vida:" + this.vidaInicial);
        Maps.eventManager.trigger("Pontos:" + 0);
        Maps.eventManager.trigger("Nivel:" + 1);
        vida = vidaInicial;
    }

    public int getVida() {
        return vida;
    }

    public Player(List<GameObject> objectsList){
        super(0, 0, 10, 10);
        this.objectsList = objectsList;
        listener = (e) -> {
            if(e instanceof String){
                String eventString = (String) e;
                // UpgradeVidaInicial
                if(eventString.startsWith("Upgrade")){
                    String[] eventStringSplit = eventString.split(":");
                    
                    String upgrade = eventStringSplit[0].substring(7);
                    if(upgrade.equals("VidaInicial")){
                        if(eventStringSplit[1].startsWith("+")){
                            this.vidaInicial += Integer.parseInt(eventStringSplit[1].substring(1));
                        }else if(eventStringSplit[1].startsWith("-")){
                            this.vidaInicial -= Integer.parseInt(eventStringSplit[1].substring(1));
                        }else{
                            this.vidaInicial = Integer.parseInt(eventStringSplit[1]);
                        }
                    }
                    if(upgrade.equals("SpawnRate")){
                        if(eventStringSplit[1].startsWith("+")){
                            this.spawnRate += Float.parseFloat(eventStringSplit[1].substring(1));
                        }else if(eventStringSplit[1].startsWith("-")){
                            this.spawnRate -= Float.parseFloat(eventStringSplit[1].substring(1));
                        }else{
                            this.spawnRate = Float.parseFloat(eventStringSplit[1]);
                        }
                    }
                    if(upgrade.equals("Spread")){
                        if(eventStringSplit[1].startsWith("+")){
                            this.spread += Float.parseFloat(eventStringSplit[1].substring(1));
                        }else if(eventStringSplit[1].startsWith("-")){
                            this.spread -= Float.parseFloat(eventStringSplit[1].substring(1));
                        }else{
                            this.spread = Float.parseFloat(eventStringSplit[1]);
                        }
                    }
                    XmlLoader.updatePlayer(this);
                }
                if(eventString.startsWith("Pontos")){
                    String[] eventStringSplit = eventString.split(":");
                    if(eventStringSplit[1].startsWith("+")){
                        this.pontuacaoAtual += Integer.parseInt(eventStringSplit[1].substring(1));
                    }else if(eventStringSplit[1].startsWith("-")){
                        this.pontuacaoAtual -= Integer.parseInt(eventStringSplit[1].substring(1));
                    }else{
                        this.pontuacaoAtual = Integer.parseInt(eventStringSplit[1]);
                    }
                    if(pontuacaoAtual > maxPontuacao){
                        maxPontuacao = pontuacaoAtual;
                        XmlLoader.updatePlayer(this);
                    }
                }
                if(eventString.startsWith("Vida")){
                    String[] eventStringSplit = eventString.split(":");
                    if(eventStringSplit[1].startsWith("+")){
                        this.vida += Integer.parseInt(eventStringSplit[1].substring(1));
                    }else if(eventStringSplit[1].startsWith("-")){
                        this.vida -= Integer.parseInt(eventStringSplit[1].substring(1));
                    }else{
                        this.vida = Integer.parseInt(eventStringSplit[1]);
                    }
                    if(vida <= 0){
                        Maps.eventManager.trigger("GameOver");
                    }
                }
            }  
        };

    }

    public void setObjectsList(List<GameObject> objectsList) {
        this.objectsList = objectsList;
    }

    private float accumulatedTime = 0;

    private boolean firstUpdate = true;

    @Override
    public void update() {
        if (firstUpdate) {
            onFirstUpdate();
            firstUpdate = false;
        }
        if (Maps.isPaused) {
            return;
        }
        super.update();
        int variationX = Scene.mousePositionX - lastPositionX;
        int variationY = Scene.mousePositionY - lastPositionY;

        this.positionX = Scene.mousePositionX - 5;
        this.positionY = Scene.mousePositionY - 5;

        this.lastPositionX = Scene.mousePositionX;
        this.lastPositionY = Scene.mousePositionY;

        float velocity = (float) Math.sqrt(Math.pow(variationX, 2) + Math.pow(variationY, 2));

        if (this.accumulatedTime > 1/spawnRate && velocity > 0) {
            PhysicsObject newInstance = new SlashParticle(this.positionX, this.positionY, 5, 5, spread);
            this.objectsList.add(newInstance);
            this.accumulatedTime = 0;
        } else {
            this.accumulatedTime += this.deltaTimeRender;
        }

    }
}
