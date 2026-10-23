package solutions.segorus.ofbiz.quickstart.wizard.steps.distribution

import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier
import solutions.segorus.ofbiz.quickstart.wizard.WizardStep

class DistributionSelectionStep extends WizardStep<DistributionSelectionConfig> {

    @Override
    protected WizardStepApplier<DistributionSelectionConfig> initRunner() {
        return new DistributionSelectionApplier()
    }

    @Override
    String getName() {
        return "Install"
    }

    @Override
    String getPath() {
        return "/setup/config/distribution"
    }
}
