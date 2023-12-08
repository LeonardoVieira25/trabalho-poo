package src.exceptions;

public class UserNotLogged extends Exception {
    public UserNotLogged() {
        super("Usuário não esta logado");
    }
}
