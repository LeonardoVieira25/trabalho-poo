/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.maps;
import java.util.List;
import src.components.GameObject;
import src.utils.EventManager;

public class Maps {
    public static boolean isPaused = false;
    public List<GameObject> objectsList;
    public List<GameObject> objectsListBuffer;
    public static EventManager eventManager = new EventManager();
    public Maps() throws Exception {}

    public void onEnter() {}
}
