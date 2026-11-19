package solutions.segorus.ofbiz.quickstart.wizard.steps.database

import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier
import groovy.json.JsonBuilder

import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

class DatabaseSetupApplier implements WizardStepApplier<DatabaseSetupConfig> {

    private StepState.Status status = StepState.Status.SCHEDULED
    private String details = ""
    private float progress = 0

    synchronized List<StepState> getStates() {
        return new StepState[] {
                new StepState(
                        status,
                        "Generate database configuration",
                        details,
                        progress
                ),
        }
    }

    static void main(String[] args) {
        var d = new DatabaseSetupApplier()
        var c = new DatabaseSetupPostgresConfig()
        c.setHostname("localhost")
        c.setUsername("postgres")
        c.setPassword("postgres")
        c.setDatabase("postgres")
        d.run(c)
    }

    @Override
    void run(DatabaseSetupConfig config) {
        synchronized (this) {
            status = StepState.Status.IN_PROGRESS
            progress = -1
            details = "Setting up database"
        }

        if (config.class == DatabaseSetupPostgresConfig)
        {
            generateHelperPlugin(config)
        }

        synchronized (this) {
            status = StepState.Status.COMPLETE
            progress = 1
            details = ""
        }
    }

    private static generateHelperPlugin(DatabaseSetupConfig config)
    {
        // -- Make paths --

        // TODO: Move to an ofbiz instance class
        File installDir = new File("install")
        File pluginsDir = new File(installDir, "plugins")
        //

        File dbHelperPluginDir = new File(pluginsDir, "QuickstartDbHelper")
        File dbHelperConfigDir = new File(dbHelperPluginDir, "config")
        dbHelperPluginDir.mkdirs()
        dbHelperConfigDir.mkdirs()

        // -- Gradle --

        // TODO: Move to a gradle config generator
        StringBuilder gradleConfigBuilder = new StringBuilder()
        gradleConfigBuilder.append("dependencies {\n")
        if (config.class == DatabaseSetupPostgresConfig)
        {
            gradleConfigBuilder.append("    pluginLibsCompile 'org.postgresql:postgresql:42.2.8'\n")
        }
        gradleConfigBuilder.append("}\n")
        String gradleConfig = gradleConfigBuilder.toString()

        File gradleConfigFile = new File(dbHelperPluginDir, "build.gradle")
        FileWriter gradleConfigWriter = new FileWriter(gradleConfigFile)
        gradleConfigWriter.write(gradleConfig)
        gradleConfigWriter.close()

        // -- OFBIZ Component --

        // TODO: Move to an ofbiz component generator
        StringBuilder ofbizComponentBuilder = new StringBuilder()
        ofbizComponentBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
        ofbizComponentBuilder.append("<ofbiz-component name=\"QuickstartDbHelper\" enabled=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"https://ofbiz.apache.org/dtds/ofbiz-component.xsd\">")

        ofbizComponentBuilder.append("    <resource-loader name=\"main\" type=\"component\"/>\n")
        ofbizComponentBuilder.append("    <classpath type=\"dir\" location=\"config\"/>\n")

        ofbizComponentBuilder.append("</ofbiz-component>\n")
        String ofbizComponent = ofbizComponentBuilder.toString()

        File ofbizComponentFile = new File(dbHelperPluginDir, "ofbiz-component.xml")
        FileWriter ofbizComponentWriter = new FileWriter(ofbizComponentFile)
        ofbizComponentWriter.write(ofbizComponent)
        ofbizComponentWriter.close()

        if (config.class == DatabaseSetupPostgresConfig)
        {
            DatabaseSetupPostgresConfig pgConfig = config

            String entityEngineConfig = buildPgConf(pgConfig)

            File entityEngineConfigFile = new File(dbHelperConfigDir, "entityengine.conf")
            FileWriter entityEngineConfigWriter = new FileWriter(entityEngineConfigFile)
            entityEngineConfigWriter.write(entityEngineConfig)
            entityEngineConfigWriter.close()
        }
    }

    private static buildPgConf(pgConfig)
    {
        JsonBuilder builder = new JsonBuilder()

        builder([
                "entityengine": [
                        "entity-config": [
                                "delegator": [
                                        "default":[
                                                "entity-model-reader": "main",
                                                "entity-group-reader": "main",
                                                "entity-eca-reader": "main",
                                                "distributed-cache-enabled": "true",
                                                "group-map": [
                                                        "org.apache.ofbiz": [
                                                                "datasource-name": "localpostgres"
                                                        ],
                                                        "org.apache.ofbiz.olap": [
                                                                "datasource-name": "localpostgresolap"
                                                        ],
                                                        "org.apache.ofbiz.tenant": [
                                                                "datasource-name": "localpostgrestenant"
                                                        ]
                                                ]
                                        ],
                                ],
                                "datasource": [
                                        "localpostgres": [
                                                "helper-class": "org.apache.ofbiz.entity.datasource.GenericHelperDAO",
                                                "schema-name": "ofbiz",
                                                "field-type-name": "postgres",
                                                "check-on-start": "true",
                                                "add-missing-on-start": "true",
                                                "use-fk-initially-deferred": "false",
                                                "alias-view-columns": "false",
                                                "join-style": "ansi",
                                                "use-binary-type-for-blob": "true",
                                                "use-order-by-nulls": "true",
                                                "offset-style": "limit",
                                                "result-fetch-size": "50",
                                                "read-data": [
                                                        ["reader-name": "tenant"],
                                                        ["reader-name": "seed"],
                                                        ["reader-name": "seed-initial"],
                                                        ["reader-name": "demo"],
                                                        ["reader-name": "ext"],
                                                        ["reader-name": "ext-test"],
                                                        ["reader-name": "ext-demo"]
                                                ],
                                                "inline-jdbc": [
                                                        "jdbc-driver": "org.postgresql.Driver",
                                                        "jdbc-uri": "jdbc:postgresql://" + pgConfig.getHostname() + "/" + pgConfig.getDatabase(),
                                                        "jdbc-username": pgConfig.getUsername(),
                                                        "jdbc-password": pgConfig.getPassword(),
                                                        "isolation-level": "ReadCommitted",
                                                        "pool-minsize": "2",
                                                        "pool-maxsize": "250",
                                                        "time-between-eviction-runs-millis": "600000"
                                                ]
                                        ],
                                        "localpostgresolap": [
                                                "helper-class": "org.apache.ofbiz.entity.datasource.GenericHelperDAO",
                                                "schema-name": "ofbiz_olap",
                                                "field-type-name": "postgres",
                                                "check-on-start": "true",
                                                "add-missing-on-start": "true",
                                                "use-fk-initially-deferred": "false",
                                                "alias-view-columns": "false",
                                                "join-style": "ansi",
                                                "use-binary-type-for-blob": "true",
                                                "use-order-by-nulls": "true",
                                                "offset-style": "limit",
                                                "result-fetch-size": "50",
                                                "read-data": [
                                                        ["reader-name": "tenant"],
                                                        ["reader-name": "seed"],
                                                        ["reader-name": "seed-initial"],
                                                        ["reader-name": "demo"],
                                                        ["reader-name": "ext"],
                                                        ["reader-name": "ext-test"],
                                                        ["reader-name": "ext-demo"]
                                                ],
                                                "inline-jdbc": [
                                                        "jdbc-driver": "org.postgresql.Driver",
                                                        "jdbc-uri": "jdbc:postgresql://" + pgConfig.getHostname() + "/" + pgConfig.getDatabase(),
                                                        "jdbc-username": pgConfig.getUsername(),
                                                        "jdbc-password": pgConfig.getPassword(),
                                                        "isolation-level": "ReadCommitted",
                                                        "pool-minsize": "2",
                                                        "pool-maxsize": "250",
                                                        "time-between-eviction-runs-millis": "600000"
                                                ]
                                        ],
                                        "localpostgrestenant": [
                                                "helper-class": "org.apache.ofbiz.entity.datasource.GenericHelperDAO",
                                                "schema-name": "ofbiz_tenant",
                                                "field-type-name": "postgres",
                                                "check-on-start": "true",
                                                "add-missing-on-start": "true",
                                                "use-fk-initially-deferred": "false",
                                                "alias-view-columns": "false",
                                                "join-style": "ansi",
                                                "use-binary-type-for-blob": "true",
                                                "use-order-by-nulls": "true",
                                                "offset-style": "limit",
                                                "result-fetch-size": "50",
                                                "read-data": [
                                                        ["reader-name": "tenant"],
                                                        ["reader-name": "seed"],
                                                        ["reader-name": "seed-initial"],
                                                        ["reader-name": "demo"],
                                                        ["reader-name": "ext"],
                                                        ["reader-name": "ext-test"],
                                                        ["reader-name": "ext-demo"]
                                                ],
                                                "inline-jdbc": [
                                                        "jdbc-driver": "org.postgresql.Driver",
                                                        "jdbc-uri": "jdbc:postgresql://" + pgConfig.getHostname() + "/" + pgConfig.getDatabase(),
                                                        "jdbc-username": pgConfig.getUsername(),
                                                        "jdbc-password": pgConfig.getPassword(),
                                                        "isolation-level": "ReadCommitted",
                                                        "pool-minsize": "2",
                                                        "pool-maxsize": "250",
                                                        "time-between-eviction-runs-millis": "600000"
                                                ]
                                        ]
                                ]
                        ]
                ]
        ])

        return builder.toPrettyString()
    }
}
