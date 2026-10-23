<#import "/template.ftl" as t>
<@t.page>
    <@t.section title="Postgress database configuration">
        <form action="/setup/config/database/postgres" method="post">
            <div class="form-group">
                <label>
                    Hostname
                    <input type="text" name="hostname" value="${(config.database.postgres.hostname)!""}"/>
                </label>
            </div>
            <div class="form-group">
                <label>
                    Username
                    <input type="text" name="username" value="${(config.database.postgres.username)!""}"/>
                </label>
            </div>
            <div class="form-group">
                <label>
                    Password:
                    <input type="password" name="password" value="${(config.database.postgres.password)!""}"/>
                </label>
            </div>
            <div class="form-group">
                <label>
                    Database:
                    <input type="text" name="database" value="${(config.database.postgres.database)!""}"/>
                </label>
            </div>

            <div class="card-option-container">
                <div class="card-info">
                    <div class="card-option-icon" style="background: #ff8822;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M12 1.67c.955 0 1.845.467 2.39 1.247l.105.16l8.114 13.548a2.914 2.914 0 0 1-2.307 4.363l-.195.008H3.882a2.914 2.914 0 0 1-2.582-4.2l.099-.185l8.11-13.538A2.91 2.91 0 0 1 12 1.67M12.01 15l-.127.007a1 1 0 0 0 0 1.986L12 17l.127-.007a1 1 0 0 0 0-1.986zM12 8a1 1 0 0 0-.993.883L11 9v4l.007.117a1 1 0 0 0 1.986 0L13 13V9l-.007-.117A1 1 0 0 0 12 8"/></svg>
                    </div>
                    <div class="card-option-body">
                        <div class="card-option-content">
                            The database must contain 3 schemas with the name ofbiz, ofbiz_olap and ofbiz_tenant
                        </div>
                    </div>
                </div>

                <button type="submit" class="card-option">
                    <div class="card-option-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m15 11l4 4l-4 4m4-4H8a4 4 0 0 1 0-8h1"/></svg>
                    </div>
                    <div class="card-option-body">
                        <div class="card-option-title">
                            Next
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </@t.section>
</@t.page>