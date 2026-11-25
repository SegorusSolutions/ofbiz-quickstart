package solutions.segorus.ofbiz.quickstart

class OfbizInstance {
    private File installDir = new File("install")
    private File pluginsDir = new File(installDir, "plugins")

    File getInstallDir() {
        return installDir
    }

    File getPluginsDir() {
        return pluginsDir
    }

    void start()
    {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(installDir)
        String[] cmd = CommandUtils.isWindows() ? ["cmd.exe", "./gradlew.bat", "ofbiz -c ofbiz.config.overload.enable=true"] : ["sh", "./gradlew", "ofbiz -c ofbiz.config.overload.enable=true"]
        builder.command(cmd);
        builder.start()
    }
}
