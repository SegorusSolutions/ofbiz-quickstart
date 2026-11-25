package solutions.segorus.ofbiz.quickstart.wizard.steps.data

import solutions.segorus.ofbiz.quickstart.CmdResult
import solutions.segorus.ofbiz.quickstart.CommandUtils
import solutions.segorus.ofbiz.quickstart.OfbizInstance
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class DataSetupApplier implements WizardStepApplier<DataSetupConfig>{
    private StepState.Status status = StepState.Status.SCHEDULED
    private String details = ""
    private float progress = 0

    synchronized List<StepState> getStates() {
        return new StepState[] {
                new StepState(
                        status,
                        "Load data",
                        details,
                        progress
                ),
        }
    }

    void run(DataSetupConfig config, OfbizInstance ofbizInstance) {
        synchronized (this) {
            status = StepState.Status.IN_PROGRESS
            progress = -1
            details = "Seeding data...<br>"
        }

        if (CommandUtils.isWindows())
        {
            CmdResult r2 = CommandUtils.runCmd("./gradlew.bat", "ofbiz -c ofbiz.config.overload.enable=true -l readers=seed,seed-initial");
            synchronized (this) {
                details += r2.stdout + r2.stderr
            }
        }
        else
        {
            CmdResult r2 = CommandUtils.runCmd("./gradlew", "ofbiz -c ofbiz.config.overload.enable=true -l readers=seed,seed-initial");
            synchronized (this) {
                details += r2.stdout + r2.stderr
            }
        }

        synchronized (this) {
            status = StepState.Status.COMPLETE
            progress = 1
            details = ""
        }
    }

}
