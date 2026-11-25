package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

class Plugin {
    private String id
    private String name
    private boolean isLocal
    private File localRepositoryPath

    Plugin(String id, String name)
    {
        this.id = id
        this.name = name
    }

    String getId() {
        return id
    }

    void setLocalRepositoryPath(File path)
    {
        this.isLocal = true
        this.localRepositoryPath = path
    }

    String getName() {
        return name
    }

    boolean isLocal() {
        return this.isLocal
    }

    File getLocalRepositoryPath() {
        return localRepositoryPath
    }
}
