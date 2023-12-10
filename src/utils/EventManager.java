/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.utils;

/*
 * Padrão de desenvolvimento Observer
 */
public class EventManager {
    private java.util.List<java.util.function.Consumer<Object>> listeners = new java.util.ArrayList<java.util.function.Consumer<Object>>();

    public void addListener(java.util.function.Consumer<Object> listener) {
        listeners.add(listener);
    }

    public void removeListener(java.util.function.Consumer<Object> listener) {
        listeners.remove(listener);
    }

    public void trigger(Object event) {
        for (java.util.function.Consumer<Object> listener : listeners) {
            listener.accept(event);
        }
    }
    
}