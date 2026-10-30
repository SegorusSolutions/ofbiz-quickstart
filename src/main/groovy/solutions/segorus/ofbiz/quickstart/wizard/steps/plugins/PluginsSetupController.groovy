package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

@Controller
@RequestMapping("/setup/config/plugins")
class PluginsSetupController {

    private Wizard wizard;

    @Autowired
    HomeController(Wizard wizard)
    {
        this.wizard = wizard;
    }

    @GetMapping()
    String setupPlugins(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/plugins")
        return "pages/setup/config/plugins"
    }

    @PostMapping()
    RedirectView setupPluginsPost()
    {
        PluginsSetupStep pluginSetupStep = wizard.getStep(PluginsSetupStep.class)

        PluginsSetupConfig config = new PluginsSetupConfig()

        pluginSetupStep.setConfig(config)

        return new RedirectView("/setup/config/database")
    }

}
