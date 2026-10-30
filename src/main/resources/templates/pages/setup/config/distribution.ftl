<#import "/template.ftl" as t>
<@t.page>
    <@t.section title="Select an OFbiz distribution">
        <form action="/setup/config/distribution" method="post">
            <div class="card-option-container">
                <#list distributions as distribution>
                    <button type="submit" name="distribution" value="${distribution.id}" class="card-option">
                        <div class="card-option-icon">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 3l8 4.5v9L12 21l-8-4.5v-9zm0 9l8-4.5M12 12v9m0-9L4 7.5"/></svg>
                        </div>
                        <div class="card-option-body">
                            <div class="card-option-title">
                                ${distribution.name}
                            </div>
                            <div class="card-option-content">
                                ${distribution.version}
                            </div>
                        </div>
                    </button>
                </#list>
            </div>
        </form>
    </@t.section>
</@t.page>