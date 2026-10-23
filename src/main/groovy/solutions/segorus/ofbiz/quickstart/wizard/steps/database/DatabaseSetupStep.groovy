package solutions.segorus.ofbiz.quickstart.wizard.steps.database

import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier
import solutions.segorus.ofbiz.quickstart.wizard.WizardStep
import solutions.segorus.ofbiz.quickstart.wizard.steps.data.DataSetupConfig

class DatabaseSetupStep extends WizardStep<DataSetupConfig> {

    @Override
    protected WizardStepApplier initRunner() {
        return new DatabaseSetupApplier()
    }

    @Override
    String getName() {
        return "Database"
    }

    @Override
    String getPath() {
        return "/setup/config/database"
    }
}
