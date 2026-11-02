<#import '/template.ftl' as t>

<@t.page>
    <@t.section title="Select a database engine">

        <form action="/setup/config/database" method="post">
            <div class="card-option-container">

                <button type="submit" name="dbEngine" value="local-h2" class="card-option">
                    <div class="card-option-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"><path d="M4 6a8 3 0 1 0 16 0A8 3 0 1 0 4 6"/><path d="M4 6v6a8 3 0 0 0 16 0V6"/><path d="M4 12v6a8 3 0 0 0 16 0v-6"/></g></svg>
                    </div>
                    <div class="card-option-body">
                        <div class="card-option-title">
                            Local H2
                        </div>
                        <div class="card-option-content">
                            Suitable for a quick setup for testing on a local machine.<br>
                            For a production setup, see the other options bellow.
                        </div>
                    </div>
                </button>

                <button type="submit" name="dbEngine" value="postgres" class="card-option">
                    <div class="card-option-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"><path d="M4 6a8 3 0 1 0 16 0A8 3 0 1 0 4 6"/><path d="M4 6v6a8 3 0 0 0 16 0V6"/><path d="M4 12v6a8 3 0 0 0 16 0v-6"/></g></svg>
                    </div>
                    <div class="card-option-body">
                        <div class="card-option-title">
                            Postgres
                        </div>
                        <div class="card-option-content">
                            Suitable for production use.
                        </div>
                    </div>
                </button>
            </div>
        </form>
    </@t.section>
</@t.page>