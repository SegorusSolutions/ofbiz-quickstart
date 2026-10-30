package solutions.segorus.ofbiz.quickstart.wizard.steps.distribution

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

@Controller
@RequestMapping("/setup/config/distribution")
class DistributionSelectionController {

    private Wizard wizard;

    @Autowired
    HomeController(Wizard wizard)
    {
        this.wizard = wizard;
    }

    @GetMapping()
    String setupDistribution(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/distribution")
        model.put("distributions", [
                [
                        "id": 1,
                        "name": "Apache OBbiz",
                        "version": "OFBIZ-12401"
                ]
        ])
        return "pages/setup/config/distribution"
    }

    @PostMapping()
    RedirectView setupDistributionPost()
    {
        DistributionSelectionStep installSetupStep = wizard.getStep(DistributionSelectionStep.class)

        DistributionSelectionConfig config = new DistributionSelectionConfig()
        config.setSource("https://github.com/nmalin/ofbiz-framework/archive/refs/heads/OFBIZ-12401.zip")

        installSetupStep.setConfig(config)

        return new RedirectView("/setup/config/plugins")
    }

}
