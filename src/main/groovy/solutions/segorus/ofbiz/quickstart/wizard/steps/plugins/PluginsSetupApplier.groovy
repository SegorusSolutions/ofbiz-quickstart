package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import solutions.segorus.ofbiz.quickstart.OfbizInstance
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class PluginsSetupApplier implements WizardStepApplier<PluginsSetupConfig>{

    private StepState.Status status = StepState.Status.SCHEDULED
    private String details = ""
    private float progress = 0

    synchronized List<StepState> getStates() {
        return new StepState[] {
                new StepState(
                        status,
                        "Installing plugins",
                        details,
                        progress
                ),
        }
    }

    void run(PluginsSetupConfig config, OfbizInstance ofbizInstance) {
        synchronized (this) {
            status = StepState.Status.IN_PROGRESS
            progress = -1
            details = "Installing plugins...<br>"
        }
        var i = 0
        var plugins = config.getPlugins()
        for (var plugin in plugins)
        {
            synchronized (this) {
                status = StepState.Status.IN_PROGRESS
                progress = i / plugins.size()
                details += "Installing " + plugin.getId() + "...<br>"
            }
            if (plugin.isLocal()) {
                var sourceDirectoryLocation = plugin.getLocalRepositoryPath().getPath()
                var destinationDirectoryLocation = new File(
                        new File(ofbizInstance.getInstallDir(), "plugins"),
                        plugin.getId(),
                ).getPath()
                Files.walk(Paths.get(sourceDirectoryLocation))
                        .forEach(source -> {
                            Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                                    .substring(sourceDirectoryLocation.length()));
                            try {
                                Files.createDirectories(destination.getParent())
                                Files.copy(source, destination);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
            else {
                // TODO: Handle other plugin repositories
            }
            i++
        }
        synchronized (this) {
            status = StepState.Status.COMPLETE
            progress = 1
            details += "Done."
        }
    }

}
