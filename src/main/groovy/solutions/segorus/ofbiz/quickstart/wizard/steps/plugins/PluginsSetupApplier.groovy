package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import solutions.segorus.ofbiz.quickstart.CommandUtils
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier

class PluginsSetupApplier implements WizardStepApplier<PluginsSetupConfig>{

    synchronized List<StepState> getStates() {
        return new StepState[] {}
    }

    void run(PluginsSetupConfig config) {

    }

}
