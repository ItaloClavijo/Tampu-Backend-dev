package pe.edu.upc.tampubackend.Security;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;
    private String firebaseToken;  // El token de Firebase que se enviará desde el frontend
    public JwtRequest() {
        super();
    }

    // Constructor con todos los parámetros
    public JwtRequest(String username, String password, String firebaseToken) {
        super();
        this.username = username;
        this.password = password;
        this.firebaseToken = firebaseToken;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}