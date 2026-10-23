package solutions.segorus.ofbiz.quickstart.wizard.steps.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import solutions.segorus.ofbiz.quickstart.wizard.Wizard

@Controller
@RequestMapping("/setup/config/database")
class DatabaseSetupController {

    private Wizard wizard;

    @Autowired
    HomeController(Wizard wizard)
    {
        this.wizard = wizard;
    }

    @GetMapping()
    String setupDatabaseEngine(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/database")
        return "pages/setup/config/database"
    }

    @PostMapping()
    RedirectView setupDatabaseEnginePost(DatabaseSetupEngineSelectionFormData formData)
    {
        if (formData.getDbEngine() == "local-h2")
        {
            DatabaseSetupStep databaseSetupStep = wizard.getStep(DatabaseSetupStep.class)
            databaseSetupStep.setConfig(new DatabaseSetupH2Config())
            return new RedirectView("/setup/config/data")
        }
        if (formData.getDbEngine() == "postgres")
        {
            return new RedirectView("/setup/config/database/postgres")
        }
        return new RedirectView("/setup/config/database")
    }

//    @GetMapping("/setup/config/database/local-h2")
//    String setupDatabaseLocalH2(ModelMap model)
//    {
//        model.put("steps", wizard.getSteps())
//        model.put("currentStep", "/setup/config/database")
//        return "pages/setup/config/database/local-h2"
//    }

    @GetMapping("/postgres")
    String setupDatabasePostgres(ModelMap model)
    {
        model.put("steps", wizard.getSteps())
        model.put("currentStep", "/setup/config/database")
        return "pages/setup/config/database/postgres"
    }

    @PostMapping("/postgres")
    RedirectView setupDatabasePostgresPost(DatabaseSetupPostgresFormData postgresFormData, ModelMap model)
    {
        model.put("steps", wizard.getSteps())

        DatabaseSetupStep databaseSetupStep = wizard.getStep(DatabaseSetupStep.class)
        DatabaseSetupPostgresConfig data = new DatabaseSetupPostgresConfig()

        data.setHostname(postgresFormData.getHostname())
        data.setUsername(postgresFormData.getUsername())
        data.setPassword(postgresFormData.getPassword())
        data.setDatabase(postgresFormData.getDatabase())
        data.setOlapDatabase(postgresFormData.getDatabase() + "_olap")
        data.setTenantDatabase(postgresFormData.getDatabase() + "_tenant")

        databaseSetupStep.setConfig(data)

        return new RedirectView("/setup/config/data");
    }
}
