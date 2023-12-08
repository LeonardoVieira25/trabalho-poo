import java.util.List;

import src.Janela;
import src.MenuInicial;
import src.Scene;
import src.maps.Map0;
import src.maps.Map1;
import src.maps.Map2;
import src.maps.Maps;

public class Main {

    public static void main(String[] args) {
        List<Maps> maps = new java.util.ArrayList<Maps>();
        maps.add(new Map0());
        maps.add(new Map1());
        maps.add(new Map2());
        boolean jogando = false;
        MenuInicial menuInicial = new MenuInicial(() -> {
            Janela janela = new Janela();
            Scene scene = new Scene(janela, maps);
        });

    }
}