package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import groovy.xml.XmlParser

class PluginsRepository {

    private boolean isPulling = false

    private Dictionary<String, Plugin> plugins = new Hashtable<>()

    boolean isPulling()
    {
        return isPulling
    }

    void pullRepository()
    {
        synchronized (this) {
            if (isPulling) {
                // Abort function if is already pulling
                return
            }
            isPulling = true
        }

        Runnable pullThread = new Runnable() {
            void run() {
                this.pullRepoInternal()
            }
        };
        new Thread(pullThread).start()
    }

    private void pullRepoInternal()
    {
        try {
            ProcessBuilder builder = new ProcessBuilder();

            if (new File("plugins", ".git").exists())
            {
                builder.directory(new File("plugins"))

                builder.command([
                        "git", "pull",
                        "--depth", "1",
                ])
            }
            else {
                builder.command([
                        "git", "clone",
                        "--depth", "1",
                        "--single-branch",
                        "--branch", "trunk",
                        "https://github.com/apache/ofbiz-plugins.git",
                        "plugins"
                ])

            }

            Process process = builder.inheritIO().start();
            int pullReturnCode = process.waitFor()
            if (pullReturnCode != 0)
            {
                throw new Exception("Failed to pull repository")
            }

            File dir = new File("plugins");
            File[] files = dir.listFiles((d, name) -> new File(new File(d,name), "ofbiz-component.xml").exists());
            for (File file in files)
            {
                Node root = new XmlParser().parse(new File(file, "ofbiz-component.xml"))
                String name = root.attributes().get("name") as String
                Plugin plugin = new Plugin("org.apache.ofbiz.plugin." + name, name)
                plugin.setLocalRepositoryPath(file)
                synchronized (this) {
                    plugins.put(plugin.getId(), plugin)
                }
            }

        }
        catch (Exception e) {
            println(e)
        }
        finally {
            synchronized (this) {
                isPulling = false
            }
        }
    }


    List<Plugin> getPlugins() {
        return plugins.elements().toList().asImmutable()
    }

    Plugin getPlugin(String id) {
        return plugins.get(id)
    }
}
