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

        DatabaseSetupPostgresConfig pgconfig = config
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
                                "jdbc-uri": "jdbc:postgresql://" + pgconfig.getHostname() + "/" + pgconfig.getDatabase(),
                                "jdbc-username": pgconfig.getUsername(),
                                "jdbc-password": pgconfig.getPassword(),
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
                                "jdbc-uri": "jdbc:postgresql://" + pgconfig.getHostname() + "/" + pgconfig.getDatabase(),
                                "jdbc-username": pgconfig.getUsername(),
                                "jdbc-password": pgconfig.getPassword(),
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
                                "jdbc-uri": "jdbc:postgresql://" + pgconfig.getHostname() + "/" + pgconfig.getDatabase(),
                                "jdbc-username": pgconfig.getUsername(),
                                "jdbc-password": pgconfig.getPassword(),
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

        var conf = builder.toPrettyString()

        FileWriter configWriter = new FileWriter("install/framework/entity/config/entityengine.conf");
        configWriter.write(conf);
        configWriter.close();

        File gradleFile = new File("install", "build.gradle");

        String gradleFileContent = Files.readString(gradleFile.toPath())

        String newGradleFileContent = gradleFileContent.replaceAll(
                "\ndependencies \\{\\r?\\n(?! {4}implementation 'org\\.postgresql:postgresql:42\\.2\\.8')",
                "\ndependencies {\n    implementation 'org.postgresql:postgresql:42.2.8'\n"
        )
        println(newGradleFileContent)
        FileWriter gradleWriter = new FileWriter(gradleFile)
        gradleWriter.write(newGradleFileContent)
        gradleWriter.close()

        synchronized (this) {
            status = StepState.Status.COMPLETE
            progress = 1
            details = ""
        }
    }
}
