package solutions.segorus.ofbiz.quickstart.wizard.steps.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

@Controller
@RequestMapping("/setup/config/user")
class UserSetupController {

    private Wizard wizard;

    @Autowired
    HomeController(Wizard wizard)
    {
        this.wizard = wizard;
    }

    @GetMapping()
    String setupUser(ModelMap model)
    {
        UserSetupStep userSetupStep = wizard.getStep(UserSetupStep.class)

        UserSetupFormData userFormData = new UserSetupFormData()
        userFormData.setName(userSetupStep.getConfig()?.getUsername())

        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/user")
        model.put("user", userFormData)
        return "pages/setup/config/user"
    }

    @PostMapping()
    RedirectView setupUserPost(UserSetupFormData userFormData, ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        UserSetupStep userSetupStep = wizard.getStep(UserSetupStep.class)
        UserSetupConfig config = new UserSetupConfig()
//
//        if (userFormData.getPassword() != userFormData.getConfirmPassword())
//        {
//            // Todo: set error message
//            return new RedirectView("/setup/config/user");
//        }

        config.setUsername(userFormData.getName())
        config.setPassword(userFormData.getPassword())

        userSetupStep.setConfig(config)
        return new RedirectView("/setup/summary");
    }
}
