package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier
import solutions.segorus.ofbiz.quickstart.wizard.WizardStep

class PluginsSetupStep extends WizardStep<PluginsSetupConfig> {
    @Override
    protected WizardStepApplier initRunner() {
        return new PluginsSetupApplier()
    }

    @Override
    String getName() {
        return "Plugins"
    }

    @Override
    String getPath() {
        return "/setup/config/plugins"
    }
}
