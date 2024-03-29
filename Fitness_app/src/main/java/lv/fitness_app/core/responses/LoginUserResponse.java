package lv.fitness_app.core.responses;


import java.util.List;

public class LoginUserResponse extends CoreResponse {
    private boolean userLogged;

    public LoginUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public LoginUserResponse(boolean userLogged) {
        this.userLogged = userLogged;
    }

    public boolean isUserLogged() {
        return userLogged;
    }
}
