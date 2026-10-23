package solutions.segorus.ofbiz.quickstart.wizard.steps.database

class DatabaseSetupPostgresConfig extends DatabaseSetupConfig {
    private String hostname
    private String username
    private String password
    private String database
    private String olapDatabase
    private String tenantDatabase

    String getTenantDatabase() {
        return tenantDatabase
    }

    void setTenantDatabase(String tenantDatabase) {
        this.tenantDatabase = tenantDatabase
    }

    String getOlapDatabase() {
        return olapDatabase
    }

    void setOlapDatabase(String olapDatabase) {
        this.olapDatabase = olapDatabase
    }

    String getDatabase() {
        return database
    }

    void setDatabase(String database) {
        this.database = database
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getHostname() {
        return hostname
    }

    void setHostname(String hostname) {
        this.hostname = hostname
    }
}
