<#import "/template.ftl" as t>
<@t.page>
    <@t.section title="Summary">
        <div class="card-option-container">
            <div class="card-info">
                <div class="card-option-icon" <#if !summaryDistributionConfig??>style="background: #ff8822;"</#if>>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 3l8 4.5v9L12 21l-8-4.5v-9zm0 9l8-4.5M12 12v9m0-9L4 7.5m12-2.25l-8 4.5"/></svg>
                </div>
                <div class="card-option-body">
                    <div class="card-option-title">
                        Selected distribution
                    </div>
                    <div class="card-option-content">
                        <#if summaryDistributionConfig??>
                            ${summaryDistributionConfig.source}
                        <#else>
                            No distribution selected
                        </#if>
                    </div>
                </div>
            </div>
            <div class="card-info">
                <div class="card-option-icon" <#if !summaryPluginsConfig??>style="background: #ff8822;"</#if>>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 7h3a1 1 0 0 0 1-1V5a2 2 0 0 1 4 0v1a1 1 0 0 0 1 1h3a1 1 0 0 1 1 1v3a1 1 0 0 0 1 1h1a2 2 0 0 1 0 4h-1a1 1 0 0 0-1 1v3a1 1 0 0 1-1 1h-3a1 1 0 0 1-1-1v-1a2 2 0 0 0-4 0v1a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1h1a2 2 0 0 0 0-4H4a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1"/></svg>
                </div>
                <div class="card-option-body">
                    <div class="card-option-title">
                        Selected plugins
                    </div>
                    <div class="card-option-content">
                        No plugins selected
                    </div>
                </div>
            </div>
            <div class="card-info">
                <div class="card-option-icon" <#if !summaryDatabaseConfig??>style="background: #ff8822;"</#if>>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"><path d="M4 6a8 3 0 1 0 16 0A8 3 0 1 0 4 6"/><path d="M4 6v6a8 3 0 0 0 16 0V6"/><path d="M4 12v6a8 3 0 0 0 16 0v-6"/></g></svg>
                </div>
                <div class="card-option-body">
                    <div class="card-option-title">
                        Database
                    </div>
                    <div class="card-option-content">
                        <#if summaryDatabaseConfig??>
                            <#if summaryDatabaseConfig.class == "class solutions.segorus.ofbiz.quickstart.wizard.steps.database.DatabaseSetupH2Config">
                                Local H2
                            <#elseif summaryDatabaseConfig.class == "class solutions.segorus.ofbiz.quickstart.wizard.steps.database.DatabaseSetupPostgresConfig">
                                Postgres<br><br>

                                <div class="grid-2">
                                    <span>Hostname:</span> <span><input type="text" value="${summaryDatabaseConfig.hostname}" readonly></span>
                                    <span>Username:</span> <span><input type="text" value="${summaryDatabaseConfig.username}" readonly></span>
                                    <span>Database:</span> <span><input type="text" value="${summaryDatabaseConfig.database}" readonly></span>
                                </div>
                                <br>
                                <div>
                                    ⚠️ The database must contain 3 schemas with the name ofbiz, ofbiz_olap and ofbiz_tenant
                                </div>
                            </#if>
                        <#else>
                            No database configuration
                        </#if>
                    </div>
                </div>
            </div>
            <div class="card-info">
                <div class="card-option-icon" <#if !summaryDataConfig??>style="background: #ff8822;"</#if>>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"><path d="M8 12.75a4 1.75 0 1 0 8 0a4 1.75 0 1 0-8 0"/><path d="M8 12.5v3.75C8 17.216 9.79 18 12 18s4-.784 4-1.75V12.5M14 3v4a1 1 0 0 0 1 1h4"/><path d="M17 21H7a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h7l5 5v11a2 2 0 0 1-2 2"/></g></svg>
                </div>
                <div class="card-option-body">
                    <div class="card-option-title">
                        Data loading
                    </div>
                    <div class="card-option-content">
                        No default data
                    </div>
                </div>
            </div>
            <div class="card-info">
                <div class="card-option-icon" <#if !summaryUserConfig??>style="background: #ff8822;"</#if>>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0-8 0M6 21v-2a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v2"/></svg>
                </div>
                <div class="card-option-body">
                    <div class="card-option-title">
                        Default user
                    </div>
                    <div class="card-option-content">
                        <#if summaryUserConfig??>
                            <div class="grid-2">
                                <span>Username:</span>
                                <span>
                                    <input type="text" value="${summaryUserConfig.username}" readonly>
                                </span>
                                <span>Password:</span>
                                <span>
                                    <input type="text" value="ofbiz" readonly> &nbsp;(change it at first login)
                                </span>
                            </div>
                        <#else>
                            No default user
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <form action="/setup/install" method="post">
            <div class="card-option-container">

                <#if summaryDistributionConfig?? && summaryPluginsConfig?? && summaryDatabaseConfig?? && summaryDataConfig?? && summaryUserConfig??>
                    <button type="submit" name="install" class="card-option">
                        <div class="card-option-icon">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 18a3 3 0 0 1-3 3H6a3 3 0 0 1-3-3M3 6a3 3 0 0 1 3-3h12a3 3 0 0 1 3 3a3 3 0 0 1-3 3H6a3 3 0 0 1-3-3m9 3v8m-3-3l3 3l3-3"/></svg>
                        </div>
                        <div class="card-option-body">
                            <div class="card-option-title">
                                Install
                            </div>
                        </div>
                    </button>
                <#else>
                    <div class="card-info">
                        <div class="card-option-icon" style="background: #ff8822;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M12 1.67c.955 0 1.845.467 2.39 1.247l.105.16l8.114 13.548a2.914 2.914 0 0 1-2.307 4.363l-.195.008H3.882a2.914 2.914 0 0 1-2.582-4.2l.099-.185l8.11-13.538A2.91 2.91 0 0 1 12 1.67M12.01 15l-.127.007a1 1 0 0 0 0 1.986L12 17l.127-.007a1 1 0 0 0 0-1.986zM12 8a1 1 0 0 0-.993.883L11 9v4l.007.117a1 1 0 0 0 1.986 0L13 13V9l-.007-.117A1 1 0 0 0 12 8"/></svg>
                        </div>
                        <div class="card-option-body">
                            <div class="card-option-title">
                                Configuration incomplete
                            </div>
                            <div class="card-option-body">
                                Please complete all steps
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </form>
    </@t.section>
</@t.page>