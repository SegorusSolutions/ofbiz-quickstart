<#import "/template.ftl" as t>
<@t.page>
    <@t.section title="Plugins">
        <form action="/setup/config/plugins" method="post">
            <div class="card-option-container">
                <button type="submit" class="card-option">
                    <div class="card-option-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.765 17.757L12 21l-8-4.5v-9l2.236-1.258m2.57-1.445L12 3l8 4.5V16m-5.439-5.441L20 7.5M12 12v9m0-9L4 7.5M3 3l18 18"/></svg>                </div>
                    <div class="card-option-body">
                        <div class="card-option-title">
                            No plugins
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </@t.section>
</@t.page>