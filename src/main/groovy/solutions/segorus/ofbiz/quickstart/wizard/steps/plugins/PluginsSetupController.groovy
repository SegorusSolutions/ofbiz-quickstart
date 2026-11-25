package solutions.segorus.ofbiz.quickstart.wizard.steps.plugins

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

import java.util.stream.Collectors

@Controller
@RequestMapping("/setup/config/plugins")
class PluginsSetupController {

    private Wizard wizard

    @Autowired
    HomeController(Wizard wizard)
    {
        this.wizard = wizard
    }

    @GetMapping()
    String setupPlugins(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/plugins")

        PluginsSetupStep pluginSetupStep = wizard.getStep(PluginsSetupStep.class)

        List<Plugin> plugins = []

        if (pluginSetupStep.getConfig() != null)
        {
            plugins = pluginSetupStep.getConfig().getPlugins()
        }

        model.put("plugins", plugins)
        return "pages/setup/config/plugins"
    }

    @GetMapping("/repository/org.apache.ofbiz.plugin")
    String listPluginsInRepo(ModelMap model)
    {
        PluginsSetupStep pluginSetupStep = wizard.getStep(PluginsSetupStep.class)
        PluginsRepository repo = wizard.getPluginsRepository()
        if (repo.getPlugins().isEmpty())
        {
            repo.pullRepository()
        }

        if (repo.isPulling())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        List<Plugin> selectedPlugins = []

        if (pluginSetupStep.getConfig() != null)
        {
            selectedPlugins = pluginSetupStep.getConfig().getPlugins()
        }

        List<Integer> plugins = repo.getPlugins().stream()
                .filter(e -> !selectedPlugins.contains(e))
                .collect (Collectors.toList());

        model.put("plugins", plugins)
        return "fragments/setup/config/plugins/plugins-in-repo"
    }

    @PostMapping()
    RedirectView setupPluginsPost(PluginForm form)
    {
        PluginsSetupStep pluginSetupStep = wizard.getStep(PluginsSetupStep.class)

        if (pluginSetupStep.getConfig() == null) {
            pluginSetupStep.setConfig(new PluginsSetupConfig())
        }

        if (form.getAdd() != null) {
            PluginsRepository repo = wizard.getPluginsRepository()
            var plugin = repo.getPlugin(form.getAdd())
            if (plugin != null)
            {
                pluginSetupStep.getConfig().addPlugin(plugin)
            }
            return new RedirectView("/setup/config/plugins")
        }

        if (form.getRemove() != null) {
            PluginsRepository repo = wizard.getPluginsRepository()
            var plugin = repo.getPlugin(form.getRemove())
            if (plugin != null)
            {
                pluginSetupStep.getConfig().removePlugin(plugin)
            }
            return new RedirectView("/setup/config/plugins")
        }

        return new RedirectView("/setup/config/database")
    }

}
