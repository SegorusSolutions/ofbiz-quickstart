package solutions.segorus.ofbiz.quickstart

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.Wizard
import solutions.segorus.ofbiz.quickstart.wizard.WizardStep
import solutions.segorus.ofbiz.quickstart.wizard.steps.data.DataSetupStep
import solutions.segorus.ofbiz.quickstart.wizard.steps.database.DatabaseSetupStep
import solutions.segorus.ofbiz.quickstart.wizard.steps.distribution.DistributionSelectionStep
import solutions.segorus.ofbiz.quickstart.wizard.steps.plugins.PluginsSetupStep
import solutions.segorus.ofbiz.quickstart.wizard.steps.user.UserSetupStep

@Configuration
class WizardBeanConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    Wizard wizard()
    {
        List<WizardStep> steps = new WizardStep[] {
                new DistributionSelectionStep(),
                new PluginsSetupStep(),
                new DatabaseSetupStep(),
                new DataSetupStep(),
                new UserSetupStep()
        }
        Wizard w = new Wizard(steps)
        return w
    }
}
