package solutions.segorus.ofbiz.quickstart.wizard

import solutions.segorus.ofbiz.quickstart.OfbizInstance

interface WizardStepApplier<T> {
    void run(T config, OfbizInstance ofbizInstance)
    List<StepState> getStates()
}