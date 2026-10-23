package solutions.segorus.ofbiz.quickstart.wizard.steps.user

import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier
import solutions.segorus.ofbiz.quickstart.wizard.WizardStep

class UserSetupStep extends WizardStep<UserSetupConfig> {

    @Override
    protected WizardStepApplier initRunner() {
        return new UserSetupApplier()
    }

    @Override
    String getName() {
        return "User"
    }

    @Override
    String getPath() {
        return "/setup/config/user"
    }
}
