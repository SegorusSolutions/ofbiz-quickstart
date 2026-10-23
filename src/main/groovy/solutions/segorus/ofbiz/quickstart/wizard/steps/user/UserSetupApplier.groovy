package solutions.segorus.ofbiz.quickstart.wizard.steps.user

import solutions.segorus.ofbiz.quickstart.CmdResult
import solutions.segorus.ofbiz.quickstart.CommandUtils
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier

class UserSetupApplier implements WizardStepApplier<UserSetupConfig> {
    private StepState.Status status = StepState.Status.SCHEDULED
    private String details = ""
    private float progress = 0

    synchronized List<StepState> getStates() {
        return new StepState[] {
                new StepState(
                        status,
                        "Create users",
                        details,
                        progress
                ),
        }
    }

    void run(UserSetupConfig config) {

        synchronized (this) {
            status = StepState.Status.IN_PROGRESS
            progress = 0
            details = "Creating user " + config.getUsername() + " ...<br>"
        }

        if (CommandUtils.isWindows())
        {
            CmdResult r2 = CommandUtils.runCmd("./gradlew.bat", "loadAdminUserLogin", "-PuserLoginId=" + config.getUsername());
            synchronized (this) {
                details += r2.stdout + r2.stderr
            }
        }
        else
        {
            CmdResult r2 = CommandUtils.runCmd("./gradlew", "loadAdminUserLogin", "-PuserLoginId=" + config.getUsername());
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
