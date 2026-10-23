package solutions.segorus.ofbiz.quickstart.wizard

interface WizardStepApplier<T> {
    void run(T config)
    List<StepState> getStates()
}