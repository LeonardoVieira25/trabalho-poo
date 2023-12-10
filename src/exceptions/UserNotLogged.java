/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src.exceptions;

public class UserNotLogged extends Exception {
    public UserNotLogged() {
        super("Usuário não esta logado");
    }
}
