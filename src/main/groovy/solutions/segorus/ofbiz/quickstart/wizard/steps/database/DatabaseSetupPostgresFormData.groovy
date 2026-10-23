package solutions.segorus.ofbiz.quickstart.wizard.steps.database

class DatabaseSetupPostgresFormData {
    private String hostname
    private String username
    private String password
    private String database

    String getHostname() {
        return hostname
    }

    void setHostname(String hostname) {
        this.hostname = hostname
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getDatabase() {
        return database
    }

    void setDatabase(String database) {
        this.database = database
    }
}
