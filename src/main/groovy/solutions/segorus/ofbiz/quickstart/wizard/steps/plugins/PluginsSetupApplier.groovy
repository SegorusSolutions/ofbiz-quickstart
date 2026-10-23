package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import solutions.segorus.ofbiz.quickstart.CommandUtils
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier

class PluginsSetupApplier implements WizardStepApplier<PluginsSetupConfig>{

    synchronized List<StepState> getStates() {
        return new StepState[] {
                new StepState(
                        StepState.Status.SCHEDULED,
                        "Download plugin *",
                        "",
                        0
                ),
                new StepState(
                        StepState.Status.SCHEDULED,
                        "Install plugin *",
                        "",
                        0
                ),
        }
    }

    void run(PluginsSetupConfig config) {

    }

}
