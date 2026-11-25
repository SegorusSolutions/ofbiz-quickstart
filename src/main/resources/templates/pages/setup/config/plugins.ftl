<#import "/template.ftl" as t>
<@t.page>
     <@t.section title="Plugins">
        <div class="split-view">
            <@t.section title="Available">
                <form action="/setup/config/plugins" method="post">
                    <div id="repo" class="card-option-container">
                        Loading...
                    </div>
                </form>
                <script>
                    function reloadStatus() {
                        fetch("/setup/config/plugins/repository/org.apache.ofbiz.plugin", {})
                            .then((response) => {
                                if (response.status == 200) return response.text();
                                else  setTimeout(reloadStatus, 1000);
                            })
                            .then((html) => {
                                if (html != undefined) document.getElementById("repo").innerHTML = html;
                            })
                            .catch((error) => {
                                console.warn(error);
                            });
                    }

                    reloadStatus();
                </script>
            </@t.section>

            <@t.section title="Selected">
                <form action="/setup/config/plugins" method="post">
                    <div class="card-option-container">
                        <#list plugins as plugin>
                            <button type="submit" class="card-option" name="remove" value="${plugin.id}">
                                <div class="card-option-icon">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.765 17.757L12 21l-8-4.5v-9l2.236-1.258m2.57-1.445L12 3l8 4.5V16m-5.439-5.441L20 7.5M12 12v9m0-9L4 7.5M3 3l18 18"/></svg>
                                </div>
                                <div class="card-option-body">
                                    <div class="card-option-title">
                                        ${ plugin.name }
                                    </div>
                                    <div class="card-option-body">
                                        ${ plugin.id }
                                    </div>
                                </div>
                            </button>
                        <#else>
                            <div class="card-info">
                                <div class="card-option-icon">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.765 17.757L12 21l-8-4.5v-9l2.236-1.258m2.57-1.445L12 3l8 4.5V16m-5.439-5.441L20 7.5M12 12v9m0-9L4 7.5M3 3l18 18"/></svg>
                                </div>
                                <div class="card-option-body">
                                    <div class="card-option-title">
                                        No plugins
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </form>
            </@t.section>

        </div>
        <form action="/setup/config/plugins" method="post">
            <div class="card-option-container">
                <button type="submit" value="Submit" class="card-option">
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