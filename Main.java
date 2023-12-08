import java.util.List;

import javax.swing.JFrame;

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
        
        boolean jogando = false;
        MenuInicial menuInicial = new MenuInicial((JFrame frame) -> {
            try{
                maps.add(new Map0());
                maps.add(new Map1(MenuInicial.getUsername(), MenuInicial.getPassword()));
                // maps.add(new Map1("user1", "password1"));
                maps.add(new Map2());
                Janela janela = new Janela();
                Scene scene = new Scene(janela, maps);
                frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
                
            }
        });

    }
}