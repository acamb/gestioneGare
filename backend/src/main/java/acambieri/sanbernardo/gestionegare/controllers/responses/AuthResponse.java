package acambieri.sanbernardo.gestionegare.controllers.responses;

import acambieri.sanbernardo.gestionegare.model.User;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private String token;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthResponse(String token,User user){
        this.token=token;
        this.user=user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
