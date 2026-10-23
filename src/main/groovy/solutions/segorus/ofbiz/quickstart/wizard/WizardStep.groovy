package solutions.segorus.ofbiz.quickstart.wizard

abstract class WizardStep<ConfigType> {

    private ConfigType config;
    private WizardStepApplier<ConfigType> runner;

    abstract protected WizardStepApplier<ConfigType> initRunner()

    WizardStep()
    {
        this.runner = initRunner();
    }

    abstract String getName();
    abstract String getPath();

    boolean isReady()
    {
        return getConfig() != null;
    }

    WizardStepApplier<ConfigType> getRunner()
    {
        return this.runner
    }

    ConfigType getConfig()
    {
        return config
    }

    void setConfig(ConfigType config)
    {
        this.config = config
    }
}
