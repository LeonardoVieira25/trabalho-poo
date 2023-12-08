package src.components;

import java.util.List;

import src.Scene;

public class Player extends PhysicsObject {
    private int id;
    private String username;
    private String password;
    private int vidaInicila;
    private int maxPontuacao;
    private float spawnRate;
    private float spread;
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getVidaInicila() {
        return vidaInicila;
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
        if(password.length() >= 8){
            this.password = password;
        }else{
            System.out.println("Senha muito curta");
        }
    }
    public void setVidaInicila(int vidaInicila) {
        this.vidaInicila = vidaInicila;
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

    public int getVida() {
        return vida;
    }
    public Player(List<GameObject> objectsList){
        super(0, 0, 10, 10);
        this.objectsList = objectsList;
    }
    public void setObjectsList(List<GameObject> objectsList) {
        this.objectsList = objectsList;
    }
    private float accumulatedTime = 0;

    @Override
    public void update() {
        super.update();
        int variationX = Scene.mousePositionX - lastPositionX;
        int variationY = Scene.mousePositionY - lastPositionY;

        this.positionX = Scene.mousePositionX - 5;
        this.positionY = Scene.mousePositionY - 5;

        this.lastPositionX = Scene.mousePositionX;
        this.lastPositionY = Scene.mousePositionY;

        float velocity = (float) Math.sqrt(Math.pow(variationX, 2) + Math.pow(variationY, 2));

        // System.out.println(velocity);

        if(this.accumulatedTime > spawnRate && velocity > 0){
            PhysicsObject newInstance = new SlashParticle(this.positionX, this.positionY, 5, 5);
            this.objectsList.add(newInstance);
            this.accumulatedTime = 0;
        }else{
            this.accumulatedTime += this.deltaTimeRender;
        }

    }
}
