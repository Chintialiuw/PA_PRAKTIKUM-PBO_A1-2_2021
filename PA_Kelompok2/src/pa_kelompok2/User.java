package pa_kelompok2;

import java.io.IOException;

public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    // GETTER & SETTER ==============================================================================

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // METHOD ========================================================================================

    public abstract void readRuangan() throws IOException, InterruptedException;
    public abstract void readPesanan() throws IOException, InterruptedException;

}
