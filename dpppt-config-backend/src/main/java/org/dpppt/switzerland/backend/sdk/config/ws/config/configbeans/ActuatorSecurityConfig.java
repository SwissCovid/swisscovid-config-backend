package org.dpppt.switzerland.backend.sdk.config.ws.config.configbeans;

public class ActuatorSecurityConfig {
    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ActuatorSecurityConfig(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
