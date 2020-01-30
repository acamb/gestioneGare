package acambieri.sanbernardo.gestionegare.controllers.requests;

import acambieri.sanbernardo.gestionegare.model.User;

public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;
    private User user;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
