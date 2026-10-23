<#import "/template.ftl" as t>
<@t.page>
    <@t.section title="Select an OFbiz distribution">
        <form action="/setup/config/distribution" method="post">
            <div class="card-option-container">
                <button type="submit" name="distribution" value="apache-ofbiz-24.09.07" class="card-option">
                    <div class="card-option-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 3l8 4.5v9L12 21l-8-4.5v-9zm0 9l8-4.5M12 12v9m0-9L4 7.5"/></svg>
                    </div>
                    <div class="card-option-body">
                        <div class="card-option-title">
                            Apache OBbiz
                        </div>
                        <div class="card-option-content">
                            24.09.07
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </@t.section>
</@t.page>