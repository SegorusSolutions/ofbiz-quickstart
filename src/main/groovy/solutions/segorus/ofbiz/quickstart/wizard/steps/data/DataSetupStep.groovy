package solutions.segorus.ofbiz.quickstart.wizard.steps.data

import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier
import solutions.segorus.ofbiz.quickstart.wizard.WizardStep

class DataSetupStep extends WizardStep<DataSetupConfig> {

    @Override
    protected WizardStepApplier initRunner() {
        return new DataSetupApplier()
    }

    @Override
    String getName() {
        return "Load data"
    }

    @Override
    String getPath() {
        return "/setup/config/data"
    }

    @Override
    boolean isReady() {
        return false
    }
}
