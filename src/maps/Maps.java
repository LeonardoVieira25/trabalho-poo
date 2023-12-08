package src.maps;

import java.util.List;

import src.components.GameObject;
import src.utils.EventManager;

public class Maps {
    public List<GameObject> objectsList;
    public List<GameObject> objectsListBuffer;
    public static EventManager eventManager = new EventManager();
    public Maps() {}
}
