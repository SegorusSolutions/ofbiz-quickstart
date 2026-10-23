package solutions.segorus.ofbiz.quickstart

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

@Controller
@RequestMapping("/")
class SetupController {

    private Wizard wizard;

    @Autowired
    SetupController(Wizard wizard)
    {
        this.wizard = wizard;
    }

    @GetMapping("/")
    String viewHome(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/")
        return "pages/home"
    }

    @GetMapping("/setup")
    String setup(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup")
        return "pages/setup"
    }

    @GetMapping("/setup/summary")
    String setupSummary(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/summary")
        return "pages/setup/summary"
    }

    @GetMapping("/setup/install")
    String setupInstall(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/install")
        model.put("busy", wizard.isRunning())
        return "pages/setup/install"
    }

    @GetMapping("/setup/install/status")
    String status(ModelMap model)
    {
        model.addAttribute("stepStates", wizard.getStepStates())
        return "fragments/setup/install-status"
    }

    @PostMapping("/setup/install")
    RedirectView install(ModelMap model)
    {
        wizard.install()
        return new RedirectView("/setup/install");
    }
}
