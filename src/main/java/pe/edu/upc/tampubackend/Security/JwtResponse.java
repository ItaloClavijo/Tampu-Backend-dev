package pe.edu.upc.tampubackend.Security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final Long userId;

    public String getJwttoken() {
        return jwttoken;
    }
    public Long getUserId() {
        return userId;
    }

    public JwtResponse(String jwttoken, Long userId) {
        super();
        this.userId = userId;
        this.jwttoken = jwttoken;
    }

}