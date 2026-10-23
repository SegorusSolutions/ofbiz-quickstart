package solutions.segorus.ofbiz.quickstart.wizard.steps.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

@Controller
@RequestMapping("/setup/config/data")
class DataSetupController {

    private Wizard wizard;

    @Autowired
    HomeController(Wizard wizard)
    {
        this.wizard = wizard;
    }

    @GetMapping()
    String setupData(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/data")
        return "pages/setup/config/data"
    }

    @PostMapping()
    RedirectView setupDataPost()
    {
        return new RedirectView("/setup/config/user")
    }
}
