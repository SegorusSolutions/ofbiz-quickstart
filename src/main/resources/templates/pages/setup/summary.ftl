<#import "/template.ftl" as t>
<@t.page>
    <@t.section title="Summary">
        <form action="/setup/install" method="post">
            <div class="card-option-container">
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
            </div>
        </form>
    </@t.section>
</@t.page>