package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

class PluginsSetupConfig {

    private List<Plugin> plugins = []

    List<Plugin> getPlugins() {
        return plugins
    }

    void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins
    }

    void addPlugin(Plugin plugin) {
        this.plugins.add(plugin)
    }

    void removePlugin(Plugin plugin) {
        this.plugins.remove(plugin)
    }
}
